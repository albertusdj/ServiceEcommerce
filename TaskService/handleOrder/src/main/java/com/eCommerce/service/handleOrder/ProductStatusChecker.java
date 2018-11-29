package com.eCommerce.service.handleOrder;

import java.net.URL;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import app.tracking_service.*;
import tracking.logistic.soa.*;

public class ProductStatusChecker implements JavaDelegate {
	private String checkProductStatus(String logisticID) throws Exception {
		URL url = new URL("http://111.221.44.148:1402/service/tracking");
		
		QName qname = new QName("soa.logistic.tracking", "Application");
		
		Service service = Service.create(url, qname);
		
		TrackingRequestArray tra = service.getPort(TrackingRequestArray.class);
		
		TrackingRequest tr = service.getPort(TrackingRequest.class);
		JAXBElement<String> secretKey = new JAXBElement<String>(qname, String.class, "vIywNgjTJTNwIHZCXyHTZgfBepWwCx");
		JAXBElement<String> uniqueID = new JAXBElement<String>(qname, String.class, logisticID );
		
		tr.setSecretKey(secretKey);
		tr.setOrderUniqueId(uniqueID);
		tra.getTrackingRequest().add(tr);
		
		Application appli = service.getPort(Application.class);
		TrackingResponse response = service.getPort(TrackingResponse.class);
		
		response = appli.getTracking(tra);
		String shipmentStatus = response.getStatus().toString();
		
		return shipmentStatus;
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String logisticID = (String) execution.getVariable("logisticID");
		
		String productStatus = checkProductStatus(logisticID);
		Integer orderID = (Integer) execution.getVariable("orderID");
		
		execution.setVariable("productStatus", productStatus);
		
		if (productStatus.equals("SHIPPED")) {
			OrderStatusChanger.setOrderStatus(orderID, 5);
			execution.setVariable("isShipped", true);
		}
		else {
			execution.setVariable("isShipped", false);
		}	
	}

}
