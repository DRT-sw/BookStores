package test;

import org.junit.Test;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserServiceTest {

	UserService userService = new UserServiceImpl();

	@Test
	public void testGetUserByUsernameAndPassword() {

		// 根据用户名和密码搜索用户记录
		User user = userService.findUserByUsernameAndPassword(new User(0,
				"admin","admin", "admin"));
		System.out.println(user);
	}

	@Test
	public void testRegisterUser() {
		// 注册用户信息
		boolean result = userService
				.saveUser(new User(3, "user1",  "user1", "user1"));
		if (result) {
			System.out.println("注册 成功 ！");
		} else {
			System.out.println("注册 失败！");
		}

	}

}
