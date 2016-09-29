package cn.liuyan.test01;

import java.io.IOException;

import adf.Test;


public class MysqlTest {

	public static void main(String[] args) {
		
		try {
//			JDBCTest.testAddNewDevice();
//			String sql = "SELECT address address,temp temp,humid humid from flowerdemo"
//					+ " WHERE address = ?";
//			System.out.println(JDBCTest.get1(Device.class, sql, "192.168.1.1"));
			String sql = "SELECT address,temp,humid from flowerdemo"
			+ " WHERE address = ?";
//			Test.test(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
