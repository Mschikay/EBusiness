package com.booking.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.booking.model.RestRoom;

@ManagedBean(name = "getRoom", eager = true)
@SessionScoped
public class GetRoom {
	
	private RootDb rootdb;
	private List <RestRoom> rooms;
	private List <Integer> roomtype;
	private List <Integer> numbers;
	private List <UIInput> inputComponents;
	
	public GetRoom() throws Exception {
		this.rooms = new ArrayList<>();
		this.roomtype = new ArrayList<>();
		this.numbers = new ArrayList<>();
		this.inputComponents = new ArrayList<>();
		this.rootdb = RootDb.getInstance();
	}

	public List<UIInput> getInputComponents() {
		return inputComponents;
	}

	public void setInputComponents(List<UIInput> inputComponents) {
		this.inputComponents = inputComponents;
	}

	public List<RestRoom> getRooms() {
		return rooms;
	}


	public void setRooms(List<RestRoom> rooms) {
		this.rooms = rooms;
	}

	public List<Integer> getRoomtype() {
		return roomtype;
	}

	public void setRoomtype(List<Integer> roomtype) {
		this.roomtype = roomtype;
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	

	public String processOrder(String number, String id) {
		System.out.println(number+id);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("orderCounts", Integer.parseInt(number));
		requestMap.put("roomtype", Integer.parseInt(id));
		return "checkout.xhtml";
	}
	
	
	public String displayAvailableRooms(String id) {
		this.rooms.clear();

		try {
			System.out.println(id);
			int idTemp = Integer.parseInt(id);
			
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		
			Map<String, Object> requestMap = externalContext.getRequestMap();
			
			Date checkin = (Date) requestMap.get("checkin");
			Date checkout = (Date) requestMap.get("checkout");
			
			this.rooms = rootdb.getAvailableRoom(checkin, checkout, idTemp);
			for (int i = 0;i < this.rooms.size();i++) {
				this.roomtype.add(this.rooms.get(i).getId());
				this.numbers.add(0);
				this.inputComponents.add(new UIInput());
			}
			
			System.out.println(this.rooms.get(0).getName());
			
			requestMap.put("rooms", this.rooms);
			requestMap.put("hotelId", idTemp);
			
		}catch (Exception e) {
			e.getMessage();
		}
		return "rooms.xhtml";
		
	}
	
}
