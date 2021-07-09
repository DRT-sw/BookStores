package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sun.org.apache.bcel.internal.generic.NEW;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;
import service.impl.UserServiceImpl;
import utils.Utils;

@WebServlet("/userServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserDao userDaoImpl = new UserDaoImpl();
	private UserService userService = new UserServiceImpl();

	public UserServlet() {
		super();
	}

	/**
	 * ajx判断用户名是否可用
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void existsUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户名
		String username = request.getParameter("username");
		// 用户名不能为空
		if ("".equals(username)) {
			return;
		}

		// 判断用户名是否存在
		boolean existsUsername = userService.existsUserName(username);
		// 返回用户是否存在
		Map<String, Integer> result = new HashMap<String, Integer>();
		// 如果用户存在，返回result 为1,如果用户不存在。result 返回0
		if (existsUsername) {
			result.put("result", 1);
		} else {
			result.put("result", 0);
		}
		// 生成Gson对象，用于把map转换成为json字符串返回
		Gson gson = new Gson();
		String responseStr = gson.toJson(result);
		response.getWriter().write(responseStr);
	}
	
	/**
	 * 管理员登陆
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void adminlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User t = new User();
		Utils.copyParamToBean(request.getParameterMap(), t);

		// 根据用户名密码根据系统
		User user = userService.findAdminByUsernameAndPassword(t);
		// 如果user不为null，说明登录成功！
		if (user != null) {
			// 把用户添加到Session对象中
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("admin", user);

			System.out.println("[" + user.getUsername() + "]用户登录成功！跳转去登录成功页面login_success.jsp");
			// 登录成功之后。转发到登录成功 页面
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
		} else {
			// 如果user == null ,说明登录失败，用户名错误，或者密码错误。
			request.setAttribute("msg", "登录失败，用户名或密码错误！");
			request.setAttribute("username", t.getUsername());
			request.getRequestDispatcher("/pages/user/admin_login.jsp").forward(request, response);
		}
	}

	/**
	 * 登录的功能方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User t = new User();
		Utils.copyParamToBean(request.getParameterMap(), t);

		// 根据用户名密码根据系统
		User user = userService.findUserByUsernameAndPassword(t);
		// 如果user不为null，说明登录成功！
		if (user != null) {
			// 把用户添加到Session对象中
			request.getSession().setAttribute("user", user);

			System.out.println("[" + user.getUsername() + "]用户登录成功！跳转去登录成功页面login_success.jsp");
			// 登录成功之后。转发到登录成功 页面
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
		} else {
			// 如果user == null ,说明登录失败，用户名错误，或者密码错误。
			request.setAttribute("msg", "登录失败，用户名或密码错误！");
			request.setAttribute("username", t.getUsername());
			System.out.println("[" + t.getUsername() + "]用户登录失败！跳转去登录页面login.jsp");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}

	}

	/**
	 * 注册的功能方法
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 把传递过来的参数赋值到javaBean对象中
		User t = new User();
		Utils.copyParamToBean(request.getParameterMap(), t);

		// 注册用户信息
		boolean result = userService.saveUser(t);
		// 如果返回true说明注册成功
		if (result) {
			// 如果注册成功。跳转到注册成功页面
			request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
		} else {
			System.out.println("用户注册失败");

			// 把错误的信息带回到jsp页面回显
			request.setAttribute("msg", "用户注册失败");
			request.setAttribute("username", t.getUsername());
			request.setAttribute("email", t.getEmail());

			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);

		}
	}

	/**
	 * 用户注销方法
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logout run.....");
		// 移除Session对象中的用户信息
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("admin");
		// 然后重定向到登录页面。或首页
		response.sendRedirect(request.getContextPath());
	}

}
