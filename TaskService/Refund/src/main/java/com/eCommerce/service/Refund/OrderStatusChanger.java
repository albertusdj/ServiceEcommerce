package com.eCommerce.service.Refund;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.camunda.bpm.engine.impl.util.json.JSONObject;

public class OrderStatusChanger {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static void setOrderStatus(int orderID, int status) throws Exception{
		String url = "http://167.205.35.223/orders/" + (new Integer(orderID)).toString();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject wrapper = new JSONObject();
		
		wrapper.put("status_id", status);
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(wrapper.toString());
		wr.flush();
		wr.close();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}
}
