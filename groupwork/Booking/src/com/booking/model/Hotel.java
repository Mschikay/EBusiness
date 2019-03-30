package com.booking.model;


public class Hotel {
	private int id;
	private String name;
	private String addr;
	private String pic;
	
	public Hotel() { }
	
	public Hotel(int id, String name, String addr, String pic) {
		this.id = id;
		this.name = name;
		this.addr = addr;
		this.pic = pic;
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
}
