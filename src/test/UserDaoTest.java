package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;

public class UserDaoTest {

	private static UserDao userDao;

	@BeforeClass
	public static void setUp() {
		System.out.println("setup");
		userDao = new UserDaoImpl();
	}

	@Test
	public void testFindUserByusernameAndPassword() {
		System.out.println(userDao.findUserByusernameAndPassword(new User(
				null, "admin", "admin", "asdf")));
		System.out.println(userDao.findUserByusernameAndPassword(new User(
				null, "admin", "xxx", "")));
	}

	@Test
	public void testSaveUser() {
		userDao.saveUser(new User(null, "bbj", "bbj", "bbj@qq.com"));
	}

	@AfterClass
	public static void setDown() {
		System.out.println("setDown");
	}


}
