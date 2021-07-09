package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.Order;
import bean.OrderItem;
import bean.Page;
import bean.User;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.Utils;

/**
 * Servlet implementation class ManagerOrderServlet
 */
@WebServlet("/managerOrderServlet")
public class ManagerOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService;

	public ManagerOrderServlet() {
		orderService = new OrderServiceImpl();
	}

	/**
	 * 所有订单
	 */
	protected void orders(HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException {

		User user = (User) request.getSession().getAttribute("user");
		// 用户未登录，需要先登录
		if (user == null) {
			// 如果用户没有登录，重定向到登录页面
			response.sendRedirect(request.getContextPath() + "/pages/user/login.jsp");
		} else {
			// 查询用户的订单信息
			List<Order> orders = orderService.queryAllOrders();
			// 设置订单到域对象中
			request.setAttribute("orders", orders);
			System.out.println(orders);
			// 转发到订单页面
			request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,
					response);
		}
	}
	
	protected void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		long pageNo = Utils.parseLong(request.getParameter("pageNo"), 1);
		long pageSize = Utils.parseLong(request.getParameter("pageSize"), Page.PAGE_SIZE);
		// 查询分页信息
		Page<Order> orderPage = orderService.page(pageNo, pageSize);
		orderPage.setUrl("managerOrderServlet?action=page");
		// 存放到域对象中
		request.setAttribute("page", orderPage);
		// 转发到某个页面显示数据
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}

	/**
	 * 确认发货
	 */
	protected void sendOrder(HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException {
		// 获取发货的订单号
		String orderId = request.getParameter("orderId");
		// 发货
		orderService.sendOrder(orderId);
		
		// 重定向到订单页面
		response.sendRedirect(request.getHeader("referer"));
	}
	
	/**
	 * 订单详情
	 * @param request
	 * @param response
	 * @throws Exception
	 * @throws IOException
	 */
	protected void Ordersmsg(HttpServletRequest request, HttpServletResponse response)
			throws Exception, IOException {
		String orderId = request.getParameter("orderId");
		List<OrderItem> items = orderService.queryOrderItems(orderId);
		System.out.println(items);
		request.setAttribute("items", items);
		request.getRequestDispatcher("/pages/order/ordermsg.jsp").forward(request, response);
	}

}