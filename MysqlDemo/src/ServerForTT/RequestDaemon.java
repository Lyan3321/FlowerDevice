package ServerForTT;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is used to deal with the connection from the client.
 * It will listens on specified port, until the connection come in.
 * It then start a handler thread to handle this connection.
 * It also provides the information of thread pool.
 * 
 * @author Liuyan
 *
 */
public class RequestDaemon {

	public static final int BIND_PORT = 8888;
	public static final RequestDaemon INSTANCE = new RequestDaemon();
	
	private ExecutorService threadPool = null;
	private static long cCon = 0;
	private ServerSocket ss;
	
	private RequestDaemon(){}
	
	public void init() throws IOException{
		init(BIND_PORT);
	}
	
	public void init(int port) throws IOException{
		threadPool = Executors.newCachedThreadPool();
		ss =new ServerSocket(port);
	}
	
	public void start() throws IOException{
		while (true){
			Socket s = ss.accept();
			cCon ++; 
			RequestHandler handler = new RequestHandler(s);
			
			threadPool.execute(handler);
		}
	}
	
}
