package com.mindtree.handsonproject.shoppingcart.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private float price;
	
	private int qty;
	
	@OneToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Product> products;
	
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
	private List<Quantity> quantity;
	
	
	public Cart() {
		super();
	}


	public Cart(int id, float price, int qty, User user, List<Product> products, List<Quantity> quantity) {
		super();
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.user = user;
		this.products = products;
		this.quantity = quantity;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}


	public List<Quantity> getQuantity() {
		return quantity;
	}


	public void setQuantity(List<Quantity> quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {	
		return "Cart [id=" + id + ", price=" + price + ", qty=" + qty + ", user=" + user + "]";
	}

}
