package com.app.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Cart {
	
	@Id
	private ObjectId _id;
	private ObjectId userId;
	private ObjectId productId;
	private long count;
	private Date date;
	
	public Cart() {}
	
	public Cart(ObjectId userId, ObjectId productId, long count) {
		this.userId = userId;
		this.productId = productId;
		this.count = count;
		this.date = new Date();
	}
	
	public ObjectId getId() {
		return _id;
	}
	
	public void setId(ObjectId id) {
		this._id = id;
	}
	
	public ObjectId getUserId() {
		return userId;
	}
	
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	
	public ObjectId getProductId() {
		return productId;
	}
	
	public void setProductId(ObjectId productId) {
		this.productId = productId;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
