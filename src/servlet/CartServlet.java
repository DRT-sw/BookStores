package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.Cart;
import bean.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.Utils;

@WebServlet("/cartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookServiceImpl();
	
	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1 获取Cart购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//2 清空购物车
		cart.clear();
	    //3 重定向回请求发起的页面
		response.sendRedirect(request.getHeader("Referer"));
	}
	
	protected void deleteItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1 获取删除的id
	    Integer id = Utils.parseInt(request.getParameter("id"), 0);
		//2 获取Cart购物车对象
	    Cart cart = (Cart) request.getSession().getAttribute("cart");
	    //3 调用deleteItem删除商品项
		cart.deleteItem(id);
	    //4 重定向回请求发起的页面
		response.sendRedirect(request.getHeader("Referer"));
	}
	
	protected void updateItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1 获取请求的参数==商品编号==商品数量
		Integer count = Utils.parseInt(request.getParameter("count"), 1);
		Integer id = Utils.parseInt(request.getParameter("id"), 0);
		//2 获取购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//3 调用updateItem方法修改商品数量
		cart.updateItem(id, count);
		//4 重定向回请求发起的页面
		response.sendRedirect(request.getHeader("Referer"));
	}
	/**
	 * 添加商品项
	 */
	protected void addItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//1 获取商品编号
		Integer id = Utils.parseInt(request.getParameter("id"), 0);
		//2 调用bookService.queryBookById(id);
		Book book = bookService.queryBookById(id);
		//3 把Book商品信息转换为CartItem
		CartItem cartItem = new CartItem(book.getId(), book.getName(), book.getPrice(), book.getPrice(), 1);
		//4 准备购物车对象
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (cart == null) {
			// 之前没有购物车
			cart = new Cart();
			// 把购物车保存到Session域中
			request.getSession().setAttribute("cart", cart);
		}
		// 把最后一个添加的商品名称，保存到Session域中
		request.getSession().setAttribute("last_name", cartItem.getName());
		
		//5调用cart.addItem添加商品项
		cart.addItem(cartItem);
		System.out.println(cart);
		//6 跳回请求发起时的页面
		response.sendRedirect(request.getHeader("Referer"));
		
	}

}
