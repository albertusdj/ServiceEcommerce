package com.eCommerce.service.handleOrder;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ProductStatusChecker implements JavaDelegate {
	private String checkProductStatus() {
		return "SHIPPED";
	}
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String productStatus = checkProductStatus();
		Integer orderID = (Integer) execution.getVariable("orderID");
		
		if (productStatus.equals("SHIPPED")) {
			OrderStatusChanger.setOrderStatus(orderID, 5);
			execution.setVariable("isShipped", true);
		}
		else {
			execution.setVariable("isShipped", false);
		}	
	}

}
