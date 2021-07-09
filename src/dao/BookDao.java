package dao;

import java.util.List;

import bean.Book;

public interface BookDao {

	/**
	 * 增加图书
	 * 
	 * @param book 添加的图书信息
	 * @return 返回insert语句，对数据库影响的行数
	 */
	public int save(Book book);

	/**
	 * 根据图书id号删除记录
	 * 
	 * @param id 要删除的图书id编号
	 * @return 返回delete语句对数据库影响的行数
	 */
	public int deleteById(int id);

	/**
	 * 更新图书信息
	 * 
	 * @param book 需要更新的图书信息
	 * @return 返回update语句对数据库影响的行数
	 */
	public int update(Book book);

	/**
	 * 查询所有图书信息
	 * 
	 * @return 返回图书信息集合。<br/>
	 *         查询失败返回null
	 */
	public List<Book> queryAllBook();

	/**
	 * 根据id查询图书信息
	 * 
	 * @param id 要查询的图书id号
	 * @return 返回图书信息<br/>
	 *         查找失败，或数据不存在，返回null
	 */
	public Book queryBookById(int id);
	
	public List<Book> queryForPageItems(long begin, long size);

	/**
	 * 查询总记录数
	 * @return
	 */
	public long queryForPageTotalCount();

	public long queryForPageTotalCount(double min, double max);

	public List<Book> queryForPageItems(long begin, long size, double min, double max);

}
