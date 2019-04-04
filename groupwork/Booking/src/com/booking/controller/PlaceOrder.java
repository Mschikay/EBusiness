package com.booking.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "placeOrder", eager = true)
@SessionScoped
public class PlaceOrder {
	private RootDb rootdb;
	
	public PlaceOrder() throws Exception {
		this.rootdb = RootDb.getInstance();
	}
	
	public String placeAnOrder(String fname, String lname) {
	
		try {			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			int counts = (int) requestMap.get("orderCounts");
			Date checkin = (Date) requestMap.get("checkin");
			Date checkout = (Date) requestMap.get("checkout");
			int hotelId = (int) requestMap.get("hotelId");
			int roomtype = (int) requestMap.get("roomtype");
			System.out.println(checkin);
			System.out.println(checkout);
			System.out.println(hotelId);
			System.out.println(roomtype);
			for (int i = 0; i < counts; i++) {
				rootdb.insertOrder(hotelId, roomtype, checkin, checkout, fname, lname);
			}
				
		}catch (Exception e) {
			e.getMessage();
		}
		return "index?redirect=true";
	}
	
	public String cancel() {
		return "index?redirect=true";
	}
}
