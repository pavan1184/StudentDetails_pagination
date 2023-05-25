package com.mindtree.handsonproject.shoppingcart.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Apparal{

	private String type;
	private String brand;
	private String design;
	
	public Apparal() {
		super();
	}

	public Apparal(String type, String brand, String design) {
		super();
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Override
	public String toString() {
		return "Apparal [type=" + type + ", brand=" + brand + ", design=" + design + "]";
	}
	
}
