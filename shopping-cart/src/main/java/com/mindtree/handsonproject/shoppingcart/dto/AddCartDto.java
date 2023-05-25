package com.mindtree.handsonproject.shoppingcart.dto;

import com.mindtree.handsonproject.shoppingcart.entity.Product;
import com.mindtree.handsonproject.shoppingcart.entity.User;

public class AddCartDto {
	
	private Product product;
	private int quantity;
	private User user;
	
	public AddCartDto() {
		super();
	}

	public AddCartDto(Product product, int quantity, User user) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "CartDto [product=" + product + ", quantity=" + quantity + ", user=" + user + "]";
	}
	
	
	
}
