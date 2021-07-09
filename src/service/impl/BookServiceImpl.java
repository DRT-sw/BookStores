package service.impl;

import java.util.List;

import bean.Book;
import bean.Page;
import dao.BookDao;
import dao.impl.BookDaoImpl;
import service.BookService;

public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	public BookServiceImpl() {
		bookDao = new BookDaoImpl();
	}

	@Override
	public boolean save(Book book) {
		// 保存图书
		int updateCount = bookDao.save(book);
		// 如果insert语句返回0以上，说明执行成功。反之亦然
		return updateCount > 0 ? true : false;
	}

	@Override
	public boolean deleteById(int id) {
		// 删除批定编号的图书
		int updateCount = bookDao.deleteById(id);
		// delete语句返回0以上。说明执行成功，反之亦然
		return updateCount > 0 ? true : false;
	}

	@Override
	public boolean update(Book book) {
		// 更新图书信息
		int updateCount = bookDao.update(book);
		// update语句返回0以上。说明执行成功，反之亦然
		return updateCount > 0 ? true : false;
	}

	@Override
	public List<Book> queryAllBook() {
		// 查询所有图书信息
		return bookDao.queryAllBook();
	}

	@Override
	public Book queryBookById(int id) {
		// 查询指定id号的图书
		return bookDao.queryBookById(id);
	}

	@Override
	public Page<Book> page(long pageNo, long pageSize) {
		Page<Book> bookPage = new Page<Book>();
		// 设置一页显示记录数
		bookPage.setPageSize(pageSize);
		
		// 总的记录数 select count(*) from t_book;
		long totalCount = bookDao.queryForPageTotalCount();
		bookPage.setTotalCount(totalCount);
		// 总的页数。公式： 总记录数 / 每页显示记录数 
		long pageTotal = totalCount / bookPage.getPageSize();
		/**
		 * 假设，我有8条记录。每页显示4条， 一共有2页 8 / 4
		 * 假设，我有9条记录。每页显示4条，一共有  1,2,3,4,   5,6,7,8,     9  
		 */
		if (totalCount % pageSize != 0) {
			pageTotal += 1;
		}
		bookPage.setPageTotal(pageTotal);
		
		// 设置分页，的当前页码
		bookPage.setPageNo(pageNo);
		
		long begin = ( bookPage.getPageNo() - 1 ) * bookPage.getPageSize();
		long size = bookPage.getPageSize();
		// 执行select * from t_book limit begin , size;
		List<Book> items = bookDao.queryForPageItems(begin , size);
		// 设置当页显示的数据集合
		bookPage.setItems(items);
		
		return bookPage;
	}

	@Override
	public Page<Book> pageByPrice(long pageNo, long pageSize, double min, double max) {
		Page<Book> bookPage = new Page<Book>();
		
		// 设置一页显示记录数
		bookPage.setPageSize(pageSize);
		
		// 总的记录数 select count(*) from t_book;
		long totalCount = bookDao.queryForPageTotalCount(min,max);
		bookPage.setTotalCount(totalCount);
		// 总的页数。公式： 总记录数 / 每页显示记录数 
		long pageTotal = totalCount / bookPage.getPageSize();
		/**
		 * 假设，我有8条记录。每页显示4条， 一共有2页 8 / 4
		 * 假设，我有9条记录。每页显示4条，一共有  1,2,3,4,   5,6,7,8,     9  
		 */
		if (totalCount % pageSize != 0) {
			pageTotal += 1;
		}
		bookPage.setPageTotal(pageTotal);
		
		// 设置分页，的当前页码
		bookPage.setPageNo(pageNo);
		
		long begin = ( bookPage.getPageNo() - 1 ) * bookPage.getPageSize();
		long size = bookPage.getPageSize();
		
		// 执行select * from t_book wher price between min and max limit begin , size;
		List<Book> items = bookDao.queryForPageItems(begin , size , min , max);
		// 设置当页显示的数据集合
		bookPage.setItems(items);
		
		return bookPage;
	}

}

