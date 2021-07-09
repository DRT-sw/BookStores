package service;

import java.util.List;

import bean.Book;
import bean.Page;

public interface BookService {

	/**
	 * 保存图书
	 * 
	 * @param book
	 *            被保存的图书
	 * @return 保存成功返回true<br/>
	 *         保存失败返回false
	 */
	public boolean save(Book book);

	/**
	 * 根据图书id删除图书
	 * 
	 * @param id
	 *            要删除的图书编号
	 * @return 删除成功true<br/>
	 *         删除失败false
	 */
	public boolean deleteById(int id);

	/**
	 * 更新图书信息
	 * 
	 * @param book
	 *            更新的图书对象
	 * @return 更新成功 true<br/>
	 *         更新失败 false
	 */
	public boolean update(Book book);

	/**
	 * 查询所有图书信息
	 * 
	 * @return 图书的集合
	 */
	public List<Book> queryAllBook();

	/**
	 * 根据图书id号查询图书
	 * 
	 * @param id
	 *            查询的id号
	 * @return 返回查询找的图书信息<br/>
	 *         图书不存在，或者查询失败返回null
	 */
	public Book queryBookById(int id);
	
	/**
	 * 查询一页数据
	 * @param pageNo 页数
	 * @param pageSize 每页显示的数据数量
	 * @return
	 */
	public Page<Book> page(long pageNo,long pageSize);

	/**
	 * 通过
	 * @param pageNo
	 * @param pageSize
	 * @param min
	 * @param max
	 * @return
	 */
	public Page<Book> pageByPrice(long pageNo, long pageSize, double min, double max);


}

