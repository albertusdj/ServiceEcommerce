package org.eCommerce.service.toko;

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
import payphone.easypay.core.ws.*;

public class CheckoutProductList implements JavaDelegate {
	private static ArrayList<Product> listOfProducts;
	
	private static final String USER_AGENT = "Mozilla/5.0";
	
	private static void getCartProduct(String username) throws Exception{
		String url = "http://167.205.35.223/users/" + username + "/cart/";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		listOfProducts = new ArrayList<Product>();
		
		JSONArray array = new JSONArray(response.toString());
		
		for (int i=0; i<array.length(); i++) {
			JSONObject temp = array.getJSONObject(i);
			int productID = Integer.parseInt(temp.getString("productID"));
			int quantity = Integer.parseInt(temp.getString("quantity"));
			int subtotal = Integer.parseInt(temp.getString("subtotal"));
			
			Product product = new Product(productID, quantity, subtotal);
			listOfProducts.add(product);
		}
	}
	
	private static int createOrder(String username) throws Exception{
		String url = "http://167.205.35.223/orders/";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject wrapper = new JSONObject();
		JSONArray details = new JSONArray();
		
		for (int i=0; i<listOfProducts.size(); i++) {
			JSONObject item = new JSONObject();
			
			item.put("id", listOfProducts.get(i).getProductID());
			item.put("quantity", listOfProducts.get(i).getQuantity());
			
			details.put(i, item);
		}
		
		wrapper.put("username", username);
		wrapper.put("details", details);
		wrapper.put("status_id", 1);
		
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
		
		JSONArray orderResponse = new JSONArray(response.toString());
		JSONObject order = orderResponse.getJSONObject(0);
		
		int orderID = Integer.parseInt(order.getString("pk"));
		
		return orderID;
	}
	
	private static String createPayment(String paymentMethod) throws Exception{
		URL url = new URL("http://167.205.35.211:8080/easypay/PaymentService?wsdl");
		
		QName qname = new QName("http://ws.core.easypay.payphone/", "PaymentService");
		
		Service service = Service.create(url, qname);
		
		PaymentService ps = service.getPort(PaymentService.class);
		
		Integer total = 0;
		
		for (int i=0; i<listOfProducts.size(); i++) {
			total += listOfProducts.get(i).getQuantity();
		}
		BigDecimal totalDecimal = new BigDecimal(total);
		
		String paymentID = ps.beginPayment(paymentMethod, totalDecimal);
		
		return paymentID;
	}
	
	private static void savePaymentID(int orderID, String paymentID) throws Exception {
		String url = "http://167.205.35.223/orders/" + orderID;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject wrapper = new JSONObject();
		
		wrapper.put("payment_id", paymentID);
		
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
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String username = (String) execution.getVariable("username");
		String paymentMethod = (String) execution.getVariable("paymentMethod");
		getCartProduct(username);
		
		Integer orderID = new Integer(createOrder(username));
		String paymentID = createPayment(paymentMethod);
		
		savePaymentID(orderID, paymentID);
		
		execution.setVariable("orderID", orderID.toString());
		execution.setVariable("paymentID", paymentID);
	}

}
