package ServerForTT;

import java.io.BufferedReader;

public class RequestDataFactory {

	public static RequestData createRequestData(BufferedReader br, String cn) throws Exception{
		
		System.out.println("creat stance");
		RequestData instance = (RequestData)Class.forName(cn).newInstance();
		System.out.println("load");
		instance.load(br);
		System.out.println("return");
		return instance;
	}
}
