package servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/baseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	this.doPost(req, resp);
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置post乱码 的字符集
		request.setCharacterEncoding("UTF-8");
		// 获取action的请求参数
		String action = request.getParameter("action");

		try {
			// 获取要执行的方法对象
			Method actionMethod = getClass().getDeclaredMethod(action, HttpServletRequest.class,
					HttpServletResponse.class);
			// 调用方法
			actionMethod.invoke(this, request, response);

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}


}
