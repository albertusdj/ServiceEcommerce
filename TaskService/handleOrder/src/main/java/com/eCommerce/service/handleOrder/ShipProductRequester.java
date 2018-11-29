package com.eCommerce.service.handleOrder;

import java.util.List;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.util.json.JSONObject;

import app.place_order_service.*;
import order.logistic.soa.*;

public class ShipProductRequester implements JavaDelegate {
	private static final String USER_AGENT = "Mozilla/5.0";
	
	public static void setLogisticID(String logisticID, int orderID) throws Exception{
		String url = "http://167.205.35.223/orders/" + (new Integer(orderID)).toString();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		JSONObject wrapper = new JSONObject();
		
		wrapper.put("logistic_id", logisticID);
		
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
	
	private static String createShipProductRequest() throws Exception{
		System.setProperty("https.protocols", "SSLv3");
		URL url = new URL("http://111.221.44.148:1402/service/place-order");
		
		QName qname = new QName("soa.logistic.order", "Application");
		
		Service service = Service.create(url, qname);
		
		OrderRequestArray ora = service.getPort(OrderRequestArray.class);
		
		OrderRequest or = service.getPort(OrderRequest.class);
		JAXBElement<String> secretKey = new JAXBElement<String>(qname, String.class, "vIywNgjTJTNwIHZCXyHTZgfBepWwCx");
		JAXBElement<Double> coordinateLat = new JAXBElement<Double>(qname, Double.class, -6.99);
		JAXBElement<Double> coordinateLong = new JAXBElement<Double>(qname, Double.class, 128.003);
		JAXBElement<String> destination = new JAXBElement<String>(qname, String.class, "-6.000,128.000");
		JAXBElement<Double> weight = new JAXBElement<Double>(qname, Double.class, 5.4);
		JAXBElement<String> receiverName = new JAXBElement<String>(qname, String.class, "John");
		JAXBElement<String> additionalDetail = new JAXBElement<String>(qname, String.class, "Warna merah ya gan");
	
		
		or.setSenderSecretKey(secretKey);
		or.setFromLat(coordinateLat);
		or.setFromLng(coordinateLong);
		or.setWeight(weight);
		or.setReceiverName(receiverName);
		or.setAdditionalDetail(additionalDetail);

		ora.getOrderRequest().add(or);
	
		Application appli = service.getPort(Application.class);
		OrderResponseArray response = service.getPort(OrderResponseArray.class);
		
		response = appli.placeOrder(ora);
		
		OrderResponse ores = service.getPort(OrderResponse.class);
		ores = response.getOrderResponse().get(0);
		
		String uniqueID = ores.getOrderUniqueId().toString();
		
		return uniqueID;
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		String uniqueID = createShipProductRequest();
		int orderID = (Integer) execution.getVariable("orderID");
		setLogisticID(uniqueID, orderID);
		
		execution.setVariable("logisticID", uniqueID);
	}

}
