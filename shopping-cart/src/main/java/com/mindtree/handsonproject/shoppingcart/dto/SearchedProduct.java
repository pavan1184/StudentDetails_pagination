package com.mindtree.handsonproject.shoppingcart.dto;

import java.util.List;

import com.mindtree.handsonproject.shoppingcart.entity.Product;

public class SearchedProduct {

	private String message;
	private List<Product> products;

	public SearchedProduct() {
		super();
	}

	public SearchedProduct(List<Product> products) {
		super();
		this.products = products;
	}

	public SearchedProduct(String message, List<Product> products) {
		super();
		this.message = message;
		this.products = products;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "SearchedProduct [message=" + message + ", products=" + products + "]";
	}

}
