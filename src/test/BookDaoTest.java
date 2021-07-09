package test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import bean.Book;
import dao.BookDao;
import dao.impl.BookDaoImpl;

public class BookDaoTest {

	private static BookDao bookDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("这是方法之前");
		bookDao = new BookDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("这是测试方法之后");
	}

	@Test
	public void testSave() {
		bookDao.save(new Book(0, "1234123", "大神是怎么样练成的", 12.12, 12, 232, null));
		System.out.println("testSave");
	}

	@Test
	public void testDeleteById() {
		System.out.println(bookDao.deleteById(16));
		System.out.println("testDeleteById");
	}

	@Test
	public void testUpdate() {
		System.out.println(bookDao.update(new Book(15, "xxxx", "xxxx", 88, 88, 88, null)));
		System.out.println("testUpdate");
	}

	@Test
	public void testQueryAllBook() {
		System.out.println(bookDao.queryAllBook());
	}

}

