package adf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server_2002_8888 {
	public static Vector<Client> clients = new Vector<Client>();

	public void startServer_2002() throws IOException {
		ServerSocket ss = new ServerSocket(8888);
		System.out.println("server start...");
		// 接受客户端请求
		while (true) {
			Socket socket = ss.accept();
			Client c = new Client(socket);

			Server_2002_8888.clients.add(c);
			c.start();
		}
	}

	public static void main(String[] args) throws IOException {
		Server_2002_8888 server = new Server_2002_8888();
		server.startServer_2002();
	}
}
