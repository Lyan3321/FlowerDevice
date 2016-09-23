package cn.liuyan.test01;

//import java.io.IOException;
//import java.io.InputStream;
//import java.sql.DriverManager;
//import java.util.Properties;
//
//import com.mysql.jdbc.Connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class JDBCTest {

	
	
	public static void testPreparedStatement() throws Exception{
	    String sql = "INSERT INTO flowerdemo (address,temp,humid) values(?,?,?)";
		
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, "192.168.1.1");
		pst.setInt(2, 55);
		pst.setInt(3, 66);
		pst.executeUpdate();
		
		stopConnection(null, conn, pst);
		
	}
	
	
	public static void addDevice(Device device) throws Exception{

		String sql = "INSERT INTO flowerdemo (address,temp,humid) values(?,?,?);";
		
		update(sql,device.getAddress(),device.getTemp(),device.getHumid());
		
	}
	
	
	public static void testAddNewDevice() throws Exception{
	
		Device device = getDeviceFromConsole();
		addDevice(device);
	}
	
	
	public static Device getDeviceFromConsole() {

		Scanner scanner = new Scanner(System.in);

		Device device = new Device();

		System.out.print("address:");
		device.setAddress(scanner.next());

		System.out.print("temp: ");
		device.setTemp(scanner.nextInt());

		System.out.print("humid:");
		device.setHumid(scanner.nextInt());


		return device;
	}
	
	public static void testResultSet() throws Exception{
	
		Connection conn = getConnection();
		Statement statement = conn.createStatement();
		String sql ="SELECT address,temp,humid FROM flowerdemo;";
		
		ResultSet rs = statement.executeQuery(sql);
		
		while(rs.next()){
			String address = rs.getString(1);
			int temp = rs.getInt(2);
			int humid = rs.getInt(3);
			
			System.out.println(address);
			System.out.println(temp);
			System.out.println(humid);
		}
		rs.close();
		statement.close();
		conn.close();
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

	
	public static void update(String sql,Object ...args) throws Exception{
		
		Connection conn = getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		
		for(int i = 0;i<args.length;i++){
			pst.setObject(i+1, args[i]);
		}
		
		pst.executeUpdate();
		
		stopConnection(null, conn, pst);
	}

	public static void stopConnection(ResultSet rs, Connection con,
			PreparedStatement pstm) {

		if (rs != null) {

			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		// 关闭执行通道
		if (pstm != null) {

			try {
				pstm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 关闭连接通道

		try {
			if (con != null && (!con.isClosed())) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}