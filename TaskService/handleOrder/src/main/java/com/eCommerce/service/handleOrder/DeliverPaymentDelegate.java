package com.eCommerce.service.handleOrder;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DeliverPaymentDelegate implements JavaDelegate {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static void sendDeliverPaymentRequest(Integer userID, Integer totalPrice) throws Exception{
		String url = "http://localhost:3000/sendPayment";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "userID=" + userID.toString() + "&cn=&totalPrice=" + totalPrice.toString();
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
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
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Integer userID = (Integer) execution.getVariable("userID");
		Integer totalPrice = (Integer) execution.getVariable("totalPrice");
		
		sendDeliverPaymentRequest(userID, totalPrice);
	}

}