package adf;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
	String name;
	Socket socket;
	BufferedReader in;
	PrintWriter out;

	public Client(Socket socket) {
		this.socket = socket;

		try {
			this.in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			this.out = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		boolean isLoop = false;
		String nm;
		try {
			nm = in.readLine();
			name = nm;
			isLoop = true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
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
						if (this.name
								.equals(Server_2002_8888.clients.get(i).name)) {
							Server_2002_8888.clients.get(i).out
									.println(this.name + ":"
											+ socket.getInetAddress() + "-->"
											+ msg);
							out.flush();
							Server_2002_8888.clients.get(i).out.flush();
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// in.close();
				out.close();
				Server_2002_8888.clients.remove(this);
				e.printStackTrace();

				break;
			}

		}
	}

}
