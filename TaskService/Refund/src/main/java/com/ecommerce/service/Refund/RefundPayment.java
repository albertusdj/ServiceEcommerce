package com.eCommerce.service.Refund;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RefundPayment implements JavaDelegate {
	 private static final String USER_AGENT = "Mozilla/5.0";
	 
	 private static void sendRefundPayment(Integer sellerID, Integer totalPrice) throws Exception {
		  String url = "http://localhost:3000/refundPayment";
		  URL obj = new URL(url);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		  //add request header
		  con.setRequestMethod("POST");
		  con.setRequestProperty("User-Agent", USER_AGENT);
		  con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		  
		  String urlParameters = "sellerID=" + sellerID.toString() + "&cn=&totalPrice=" + totalPrice.toString();
		    
		  // Send post request
		  con.setDoOutput(true);
		  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		  wr.writeBytes(urlParameters);
		  wr.flush();
		  wr.close();
		  
		  
		  int responseCode = con.getResponseCode();
		  System.out.println("\nSending 'POST' request to URL : " + url);
		  System.out.println("Response Code : " + responseCode);
		  
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
		Integer orderID = (Integer) execution.getVariable("orderID");
		int statusID = 9;
		
		OrderStatusChanger.setOrderStatus(orderID, statusID);
	}

}
