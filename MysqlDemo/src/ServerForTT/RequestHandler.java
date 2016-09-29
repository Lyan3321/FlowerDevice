package ServerForTT;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Handler;


/**
 * To process the request using this class.
 * 
 * @author Liuyan
 *
 */
public class RequestHandler implements Runnable{

	private Socket s = null;
	private BufferedReader br = null;
	private BufferedWriter bw = null;
	private RequestData data;
	
	Vector<RequestDataRecievedListener> requestDataListeners = new Vector<RequestDataRecievedListener>();
	
	public RequestHandler(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	}
	
	@Override
	public void run() {

		System.out.println("run");
		try {
			data = RequestDataFactory.createRequestData(br,Server.INSTANCE.getServerConfig().getConfigValue(ServerConfig.REQUESTDATACLASS));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void release(){
		try {
			br.close();
			bw.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
