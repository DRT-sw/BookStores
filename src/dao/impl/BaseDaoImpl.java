package dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.JDBCUtils;

/**
 * 基础持久化类
 * 
 * @author Suowei
 *
 * @param <T>
 */
public abstract class BaseDaoImpl<T> {
	// DBUtils 的sql语句执行的类
	protected QueryRunner queryRunner;
	// 泛型的类型
	protected Class<T> type;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		queryRunner = new QueryRunner();
		// 获取带有泛型的父类
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();

		// 获取泛型类型
		type = (Class<T>) superClass.getActualTypeArguments()[0];
	}

	/**
	 * 查询返回一个对象
	 * 
	 * @param sql
	 *            查询的sql语句
	 * @param params
	 *            查询的参数
	 * @return 返回查询对象的实例<br/>
	 *         如果返回null,则没有查到任何记录
	 */
	public T queryOne(String sql, Object... params) {
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = JDBCUtils.getConnection();
			// 执行查询语句，并且传进去一个BeanHandler处理resultSet包装
			return queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回查询的对象集合
	 * 
	 * @param sql
	 *            sql语句
	 * @param params
	 *            sql的参数
	 * @return 返回查询的对象集合。
	 */
	public List<T> queryList(String sql, Object... params) {
		Connection conn = null;
		try {
			// 获取数据库连接
			conn = JDBCUtils.getConnection();
			// 执行查询语句，并且传进去一个BeanHandler处理resultSet包装
			return queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 执行insert，update，delete语句
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param params
	 *            sql的参数
	 * @return 返回语句执行影响的行数。
	 */
	public int update(String sql, Object... params) {
		Connection conn = null;
		int count = 0;
		try {
			conn = JDBCUtils.getConnection();
			// 执行sql语句，返回影响的行数
			count = queryRunner.update(conn, sql, params);
			// 如果影响行数大于0，说明有影响。返回true
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return count;
	}
	
	public Object querySingleValue(String sql, Object... params) {
		Connection connection = null;

		try {
			// 获取数据库连接
			connection = JDBCUtils.getConnection();
			// 执行查询语句 
			return queryRunner.query(connection, sql, new ScalarHandler(), params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	/**
	 * 执行批量操作
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param params
	 *            参数
	 * @return 返回每个语句修改的数量
	 * @throws Exception 
	 */
	public int[] batch(String sql, Object[][] params) throws Exception {
		Connection connection = null;

		try {
			// 获取数据库连接
			connection = JDBCUtils.getConnection();
			// 执行查询语句
			return queryRunner.batch(connection, sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}
}
