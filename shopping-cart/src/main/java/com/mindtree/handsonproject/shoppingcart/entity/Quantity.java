package com.mindtree.handsonproject.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Quantity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@OneToOne
	private Product product;
	
	private int quantity;
	
	@ManyToOne()
	private Cart cart;

	public Quantity() {
		super();
	}

	public Quantity(int id, Product product, int quantity, Cart cart) {
		super();
		this.id = id;
//		this.product = product;
		this.quantity = quantity;
		this.cart = cart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Quantity [id=" + id + ", product="  + ", quantity=" + quantity + ", cart=" + cart + "]";
	}
	
	
}
