package com.app.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Product {
	
	@Id
	private ObjectId _id;
	private String name;
	private double price;
	private long count;
	
	public Product() {}
	
	public Product(String name, double price, long count) {
		this.name = name;
		this.price = price;
		this.count = count;
	}
	
	public ObjectId getId() {
		return _id;
	}
	public void setId(ObjectId id) {
		this._id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
