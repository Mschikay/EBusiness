package com.booking.controller;

import com.booking.controller.RootDb;
import com.booking.model.Hotel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Test extends HttpServlet{
	
	public Test() {}
		
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	    try {
	    	String checkInDate = "2019-03-28";
			String checkOutDate = "2019-03-30";
			Date checkin = Date.valueOf(checkInDate);
			Date checkout = Date.valueOf(checkInDate);
			System.out.println(checkin);
			RootDb rootdb = RootDb.getInstance();
			rootdb.insertOrder(1, 1, checkin, checkout, "Ziqi", "Tang");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String args[]) throws Exception {
		try {
	    	String checkInDate = "2019-03-28";
			String checkOutDate = "2019-03-30";
			Date checkin = Date.valueOf(checkInDate);
			Date checkout = Date.valueOf(checkOutDate);
			System.out.println(checkin);
			RootDb rootdb = RootDb.getInstance();
			rootdb.insertOrder(1, 1, checkin, checkout, "Ziqi", "Tang");
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
