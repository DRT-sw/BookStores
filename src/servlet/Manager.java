package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;

/**
 * Servlet implementation class Manager
 */
@WebServlet("/manager")
public class Manager extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		User admin = (User) request.getSession().getAttribute("admin");
		if (admin == null) {
			request.getRequestDispatcher("/pages/user/admin_login.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/pages/manager/manager.jsp").forward(request, response);
		}
	
	}

}
