package com.booking.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.booking.model.*;

@ManagedBean(name = "getHotel", eager = true)
@SessionScoped
public class GetHotel {
	
	private List <Hotel> hotels;
	private RootDb rootdb;
	
	public GetHotel() throws Exception {
		this.hotels = new ArrayList<>();
		this.rootdb = RootDb.getInstance();
	}

	public List<Hotel> getHotels() {
		return hotels;
	}

	public void setHotels(List<Hotel> hotels) {
		this.hotels = hotels;
	}


	public String displayAvailableHotels(String checkin, String checkout, String addrTemp, String people) throws Exception {
		this.hotels.clear();
		try {
			System.out.println(checkin+checkout+addrTemp+people);
	        
			Date checkoutTemp = Date.valueOf(checkout.substring(6, 10)+"-"+checkout.substring(0, 2)+"-"+checkout.substring(3, 5));
			Date checkinTemp = Date.valueOf(checkin.substring(6, 10)+"-"+checkin.substring(0, 2)+"-"+checkin.substring(3, 5));		
			int peopleTemp = Integer.parseInt(people);
			this.hotels = rootdb.getAvailableHotel(checkinTemp, checkoutTemp, addrTemp, peopleTemp);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			System.out.println(this.hotels.get(0).getName());
			
			requestMap.put("hotels", this.hotels);
			requestMap.put("checkin", checkinTemp);
			requestMap.put("checkout", checkoutTemp);
			
		}catch (Exception e) {
			e.getMessage();
		}
		return "hotels.xhtml";
	}
		
}
