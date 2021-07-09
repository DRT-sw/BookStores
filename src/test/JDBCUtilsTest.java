package test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import utils.JDBCUtils;


public class JDBCUtilsTest {


	@Test
	public void test() throws Exception {
	
			Connection conn = JDBCUtils.getConnection();
			System.out.println(conn);
			JDBCUtils.closeResource(conn, null, null);
		
	}

}
