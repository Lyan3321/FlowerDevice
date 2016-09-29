package adf;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import cn.liuyan.test01.JDBCTest;

public class Test {

	// String sql = null; 5c:cf:7f:1b:56:97

	public static String query(String sql,String name) throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		conn = getConnection();
		pst = conn.prepareStatement(sql);
		pst.setObject(1, name);
		rs = pst.executeQuery();
		if (rs.next()) {
			String value = rs.getString(1);
			System.out.println(value);
			return value;
		}else{
			return null;
		}
	}

	public static void update(String sql, String name) throws Exception {

		Connection conn = null;
		PreparedStatement pst = null;
		
		conn = JDBCTest.getConnection();
		pst= conn.prepareStatement(sql);
		pst.setObject(1, name);
		pst.executeUpdate();
	}
	
	public static Connection getConnection() throws Exception {
		Properties properties = new Properties();

		InputStream in = JDBCTest.class.getClassLoader().getResourceAsStream(
				"jdbc.properties");
		properties.load(in);

		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String jdbcUrl = properties.getProperty("jdbcUrl");
		String driver = properties.getProperty("driverClass");

		Class.forName(driver);

		return DriverManager.getConnection(jdbcUrl, user, password);
	}
}

