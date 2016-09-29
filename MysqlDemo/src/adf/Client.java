package adf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import cn.liuyan.test01.JDBCTest;

public class Client extends Thread {
	String name;
	Socket socket;
	BufferedReader in;
	PrintWriter out;

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	String sql = null;

	public Client(Socket socket) {
		this.socket = socket;

		try {
			this.in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		boolean isLoop = false;
		String nm;
		System.out.println("开始读取数据");
		try {
			nm = in.readLine();
			name = nm;

			System.out.println(name);
			isLoop = true;
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		while (isLoop) {
			// 接受客户端传来的数据
			String msg;
			try {
				msg = in.readLine();
				System.out.println(msg);
				// 若用户输入的exit则退出
				if (msg.trim().equals("exit")) {
					isLoop = false;// 退出循环
					socket.close();
					in.close();
					out.close();
					Server_2002_8888.clients.remove(this);
					return;
				} else {
					for (int i = 0; i < Server_2002_8888.clients.size(); i++) {
						if (this.name.equals(Server_2002_8888.clients.get(i).name)) {

							switch (msg){
							//接受设备更改状态请求，00为关闭
							case "[ZZZ 00]":
								Test.update("UPDATE mac SET temp = 0 WHERE mac_address = ?",name);
								break;
							//接受设备更改状态请求，01为开启
							case "[ZZZ 01]":
								Test.update("UPDATE mac SET temp = 1 WHERE mac_address = ?",name);
								break;
							//接受手机端查询请求
							case "{ZZZ}":
								msg = Test.query("SELECT temp from mac WHERE mac_address = ?",name);
								Server_2002_8888.clients.get(i).out.println(this.name + ":"
										+ socket.getInetAddress() + "-->"
										+ msg);
								break;
							//接受手机端更改，00为关闭，01为开启
							case "{ZZZ 0}":
								Server_2002_8888.clients.get(i).out.println(this.name + ":"
										+ socket.getInetAddress() + "-->"
										+ msg);
								break;
								
							case "{ZZZ 1}":
								Server_2002_8888.clients.get(i).out.println(this.name + ":"
										+ socket.getInetAddress() + "-->"
										+ msg);
								break;
							}
							
							out.flush();
							Server_2002_8888.clients.get(i).out.flush();
						}
					}
				}
			} catch (IOException e) {
//				 in.close();
				out.close();
				Server_2002_8888.clients.remove(this);
				e.printStackTrace();

				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}
