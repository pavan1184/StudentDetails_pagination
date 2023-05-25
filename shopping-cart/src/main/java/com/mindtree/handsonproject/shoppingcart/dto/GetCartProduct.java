package com.mindtree.handsonproject.shoppingcart.dto;

import java.util.List;

public class GetCartProduct {

	private List<CartProductDto> products;
	private float totalAmount ;
	
	public GetCartProduct() {
		super();
	}

	public GetCartProduct(List<CartProductDto> products, float totalAmount) {
		super();
		this.products = products;
		this.totalAmount = totalAmount;
	}

	public List<CartProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<CartProductDto> products) {
		this.products = products;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		return "GetCartProduct [products=" + products + ", totalAmount=" + totalAmount + "]";
	}
	

}
