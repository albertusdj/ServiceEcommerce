package com.eCommerce.service.Refund;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RefundDelegate implements JavaDelegate {
	 private static final String USER_AGENT = "Mozilla/5.0";
	 
	 private static int getOrderStatus(Integer orderID) throws Exception {
		  String url = "http://167.205.35.223/orders/"
		    + orderID.toString();
		  URL obj = new URL(url);
		  HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		  //add request header
		  con.setRequestMethod("GET");
		  con.setRequestProperty("User-Agent", USER_AGENT);
		  con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	
		  int responseCode = con.getResponseCode();
		  
		  BufferedReader in = new BufferedReader(
		    new InputStreamReader(con.getInputStream()));
		  String inputLine;
		  StringBuffer response = new StringBuffer();
	
		  while ((inputLine = in.readLine()) != null) {
		   response.append(inputLine);
		  }
		  in.close();
		  
		  JSONArray orderResponse = new JSONArray(response.toString());
		  JSONObject order = orderResponse.getJSONObject(0);
			
		  int orderStatus = Integer.parseInt(order.getString("pk"));
			
		  return orderStatus;
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 Integer orderID = (Integer) execution.getVariable("orderID");
		 Integer orderStatus = new Integer(getOrderStatus(orderID));
		 
		 execution.setVariable("orderReceivedStatus",orderStatus >= 7);
	}

}
