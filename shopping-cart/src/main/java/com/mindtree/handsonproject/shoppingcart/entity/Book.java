package com.mindtree.handsonproject.shoppingcart.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Book{

	private String genre;
	private String authour;
	private String publication;
	
	public Book() {
		super();
	}

	public Book(String genre, String authour, String publication) {
		super();
		this.genre = genre;
		this.authour = authour;
		this.publication = publication;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthour() {
		return authour;
	}

	public void setAuthour(String authour) {
		this.authour = authour;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	@Override
	public String toString() {
		return "Book [genre=" + genre + ", authour=" + authour + ", publication=" + publication + "]";
	}

	
	
}
