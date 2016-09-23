package cn.liuyan.test01;

import java.io.IOException;


public class MysqlTest {

	public static void main(String[] args) {
		
		try {
//			JDBCTest.testAddNewDevice();
			String sql = "SELECT address address,temp temp,humid humid from flowerdemo"
					+ " WHERE address = ?";
			JDBCTest.get(Device.class, sql, "192.168.1.1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
