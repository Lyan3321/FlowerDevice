package ServerForTT;

import java.io.IOException;

/**
 * This class is used as the main class for JVM to load at start-up. It will
 * load some objects, initialize and start the thread of serversocket.
 * 
 * @author Liuyan
 *
 */
public class Server {

	public static final String DEAFLT_CONFIG_FILEPATH = "server.conf";

	public static Server INSTANCE = new Server();
	private ServerConfig sc = null;
	private RequestDaemon rd;
	
	private Server(){}
	
	public static void main(String[] args){
		System.out.println("Just for test...Don't be nerver！");
		
		try {
			INSTANCE.init();
			
		} catch (Exception e1) {
			System.out.println("Cannot initialize the server, please check the configuration and re-start the program.");
			e1.printStackTrace();
			return;
		}
		
		try {
			INSTANCE.start();
			System.out.println("Exit...");
		} catch (Exception e) {
			System.out.println("Cannot start Server,please check the configuration and re-start the program.");
			e.printStackTrace();
		}
	}

	private void init() throws IOException {

		sc = new ServerConfig();
		sc.load(DEAFLT_CONFIG_FILEPATH);
		
		rd = RequestDaemon.INSTANCE;
		rd.init();
		
		System.out.println("Server has been initialized");
		
	}
	
	private void start() throws IOException {

		rd.start();
	}
	
	public ServerConfig getServerConfig(){
		return sc;
	}

	
}
