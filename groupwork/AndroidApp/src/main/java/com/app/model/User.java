package com.app.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	private ObjectId _id;
	private String username;
	private String email;
	private String password;
	
	public User() {}
	
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User(ObjectId id, String username, String email, String password) {
		this._id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public ObjectId getId() {
		return this._id;
	}
	
	public void setId(String id) {
		this._id = new ObjectId(id);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}	
}
