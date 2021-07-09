package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
	/**
	 * 使用ThreadLocal保存Connection对象
	 */
	private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();


	/**
	 * 从数据库连接池获取链接
	 * @return	
	 */
	public static Connection getConnection() throws Exception {
			// 先从ThreadLocal中获取
			Connection connection = connectionThreadLocal.get();
			try {
				if (connection == null) {
					connection = cpds.getConnection();
					// 设置事务为手动提交
//					connection.setAutoCommit(false);
					connectionThreadLocal.set(connection);
				} 
			} catch (Exception e) {
				e.printStackTrace();
			}
			return connection;
	}
	
	/**
	 * 关闭连接流
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeResource(Connection conn, Statement st, ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放数据库连接
	 */
	@SuppressWarnings("unused")
	public static void closeConnection() {
		Connection conn = null;
		// 从线程ThreadLocal中获取
		if (conn != null) {
			try {
				conn = getConnection();
				// 事务提交
				conn.commit();
				// 事务关闭
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 移除
		closeResource(conn, null, null);
	}

	@SuppressWarnings("unused")
	public static void rollback() {
		// 从线程ThreadLocal中获取
		Connection conn = null;
		if (conn != null) {
			try {
				conn = getConnection();
				// 事务回滚
				conn.rollback();
				// 关闭连接
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 移除
		closeResource(conn, null, null);
	}
	@SuppressWarnings("unused")
	public static void commit() {
		// 从线程ThreadLocal中获取
		Connection conn = null;
		if (conn != null) {
			try {
				conn = getConnection();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
}
