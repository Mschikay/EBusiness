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

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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