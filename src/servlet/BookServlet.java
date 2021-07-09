package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import bean.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import utils.Utils;

/**
 * 图书web模块
 */
@WebServlet("/bookServlet")
public class BookServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	// 处理图书业务
	private BookService bookService;

	public BookServlet() {
		bookService = new BookServiceImpl();
	}
	
	/**
	 * 搜索图书信息显示
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 查找所有图书信息
		List<Book> books = bookService.queryAllBook();
		// 保存到请求域对象中
		request.setAttribute("books", books);
		//转到了图书管理列表页面
//		System.out.println("转跳到book_manager.jsp");
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		Utils.copyParamToBean(request.getParameterMap(), book);
		bookService.save(book);
		response.sendRedirect(request.getContextPath() + "/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = new Book();
		Utils.copyParamToBean(request.getParameterMap(), book);

		bookService.update(book);
		response.sendRedirect(request.getContextPath() + "/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}

	protected void getBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Utils.parseInt(request.getParameter("id"), 0);
		Book book = bookService.queryBookById(id);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			String idStr = request.getParameter("id");
			int id = Utils.parseInt(idStr, 0);
			bookService.deleteById(id);
		response.sendRedirect(request.getContextPath() + "/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}


	protected void page(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取参数
		long pageNo = Utils.parseLong(request.getParameter("pageNo"), 1);
		long pageSize = Utils.parseLong(request.getParameter("pageSize"), Page.PAGE_SIZE);
		// 查询分页信息
		Page<Book> bookPage = bookService.page(pageNo, pageSize);
		bookPage.setUrl("bookServlet?action=page");
		// 存放到域对象中
		request.setAttribute("page", bookPage);
		// 转发到某个页面显示数据
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	
}
