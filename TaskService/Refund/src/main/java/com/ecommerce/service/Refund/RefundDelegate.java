package com.ecommerce.service.Refund;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RefundDelegate implements JavaDelegate {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static String getOrderStatus(Integer orderID) throws Exception {
		String url = "http://localhost:3000/orderStatus?orderID="
				+ orderID.toString();
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer orderID = (Integer) execution.getVariable("orderID");
	
		execution.setVariable("orderReceivedStatus",getOrderStatus(orderID));
	}
}
