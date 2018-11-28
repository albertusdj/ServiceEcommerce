package org.eCommerce.service.toko;

import java.awt.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONArray;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import payphone.easypay.core.ws.PaymentEvent;
import payphone.easypay.core.ws.PaymentService;

public class GetPaymentStatus implements JavaDelegate {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static void setOrderStatus(int orderID, int status) throws Exception{
		String url = "http://167.205.35.223/orders/" + orderID;
		
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
	
	private static String getPaymentStatus(String paymentID) throws Exception {
		URL url = new URL("http://167.205.35.211:8080/easypay/PaymentService?wsdl");
		
		QName qname = new QName("http://ws.core.easypay.payphone/", "PaymentService");
		
		Service service = Service.create(url, qname);
		
		PaymentService ps = service.getPort(PaymentService.class);
		
		ArrayList<PaymentEvent> paymentEvents = new ArrayList<PaymentEvent>(ps.getPaymentEvents(paymentID, null).getEvents());
		
		String paymentStatus = paymentEvents.get(paymentEvents.size()-1).getType().toString();
		
		return paymentStatus;
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String paymentID = (String) execution.getVariable("paymentID");
		
		int orderID = Integer.parseInt((String) execution.getVariable("orderID"));
		int status_id = 2;
		
		String paymentStatus = getPaymentStatus(paymentID);
		
		if (paymentStatus.equals("SUCCESS")) {
			status_id = 3;
		}
		
		execution.setVariable("paymentStatus", paymentStatus);
		
		setOrderStatus(orderID, status_id);
	}
}
