package cn.liuyan.test01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerDemo {

	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(15233);
			Socket s = ss.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
