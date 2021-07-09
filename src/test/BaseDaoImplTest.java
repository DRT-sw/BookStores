package test;



import org.junit.Test;

import dao.UserDao;
import dao.impl.UserDaoImpl;

public class BaseDaoImplTest {

	@Test
	public void test() {
		UserDao userDao = new UserDaoImpl();
	}

}
