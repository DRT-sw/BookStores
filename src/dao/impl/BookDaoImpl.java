package dao.impl;

import java.util.List;

import bean.Book;
import dao.BookDao;
import utils.JDBCUtils;
import utils.Utils;

public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao {

	@Override
	public int save(Book book) {
		String sql = "INSERT INTO t_book(`id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`) VALUES(NULL , ? , ? , ? , ? , ? , ?)";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),
				book.getStock(), book.getImgPath());
	}

	@Override
	public int deleteById(int id) {
		String sql = "delete from t_book where id = ?";
		return update(sql, id);
	}

	@Override
	public int update(Book book) {
		String sql = "update t_book set name = ? ,author = ? ,price = ? ,sales = ? ,stock = ? , img_path = ? where id = ?";
		return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(),
				book.getStock(), book.getImgPath(), book.getId());
	}

	@Override
	public List<Book> queryAllBook() {
		String sql = "select id,`name`, author, price,sales,stock, img_path imgPath from t_book";
		return queryList(sql);
	}

	@Override
	public Book queryBookById(int id) {
		String sql = "select id,`name`, author, price,sales,stock, img_path imgPath from t_book where id = ?";
		return queryOne(sql, id);
	}

	@Override
	public List<Book> queryForPageItems(long begin, long size) {
		String sql = "select id,`name`, author, price,sales,stock, img_path  from t_book limit ? , ?";

		List<Book> items = queryList(sql, begin, size);

		return items;
	}

	@Override
	public long queryForPageTotalCount() {
		String sql = "select count(*) from t_book";

		Object result = querySingleValue(sql);

//		System.out.println("执行完之后" + result);

		return Utils.parseLong(result.toString(), 0);
	}

	@Override
	public long queryForPageTotalCount(double min, double max) {
		String sql = "select count(*) from t_book where price between ? and ?";

		Object result = querySingleValue(sql,min,max);

//		System.out.println("执行完之后" + result);

		return Utils.parseLong(result.toString(), 0);
	}

	@Override
	public List<Book> queryForPageItems(long begin, long size, double min, double max) {

		String sql = "select id,`name`, author, price,sales,stock, img_path imgPath from t_book where price between ? and ? limit ? , ?";

		List<Book> items = queryList(sql, min, max, begin, size);

		return items;
	}

}
