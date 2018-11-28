package org.eCommerce.service.toko;

public class Product {
	private int productID;
	private int quantity;
	private int subtotal;
	
	public Product(int productID, int quantity, int subtotal) {
		this.productID = productID;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}
	
	public int getProductID() {
		return productID;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getSubtotal() {
		return subtotal;
	}
}
