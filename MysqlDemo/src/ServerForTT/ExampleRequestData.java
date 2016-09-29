package ServerForTT;

import java.io.BufferedReader;
import java.io.IOException;

public class ExampleRequestData implements RequestData {

	private String macName;
	private String requestMsg;
	boolean flag = false;

	@Override
	public void load(BufferedReader br) throws IOException {

		// 获取链接设备的mac地址
		macName = br.readLine();
		if (macName != null) {
			flag = true;
		} else {

		}

		while (flag) {

			requestMsg = br.readLine();
			System.out.println(requestMsg);
			if(requestMsg.trim().equals("exit")){
				flag = false;

			}
		}

	}

}
