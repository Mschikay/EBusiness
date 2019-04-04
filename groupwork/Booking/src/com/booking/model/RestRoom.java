package com.booking.model;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RestRoom {


	private int id;
	
	private String name;

	private int restCount;
	
	private int orderCount;
	
	private double price;
	
	
	public RestRoom () { }
	
	public RestRoom (int id, String name, int restCount, double price) {
		this.id = id;
		this.name = name;
		this.restCount = restCount;
		this.price = price;
		this.orderCount = 1;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRestCount() {
		return restCount;
	}

	public void setRestCount(int restCount) {
		this.restCount = restCount;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}


	
}
