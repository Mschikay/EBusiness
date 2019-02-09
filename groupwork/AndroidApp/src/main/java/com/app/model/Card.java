package com.app.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Card {
	
	@Id
	private ObjectId _id;
	private String email;
	private String cardNumber;
	private double balance;
	
	public Card() {}
	
	public Card(String email, String cardNumber, double balance) {
		this.email = email;
		this.cardNumber = cardNumber;
		this.balance = balance;
	}
	
	public ObjectId getId() {
		return _id;
	}
	
	public void setId(ObjectId id) {
		this._id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setUserId(String email) {
		this.email = email;
	}
	
	public String getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}