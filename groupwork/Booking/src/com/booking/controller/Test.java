package com.booking.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class Test extends HttpServlet{
	
	public Test() {}
	
	@Resource(name = "jdbc/bank")
	private DataSource ds;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	    try {
	       
	        
	        Connection con = ds.getConnection();
	                     
	        Statement stmt = con.createStatement();
	        String query = "select * from location";
	        ResultSet rs = stmt.executeQuery(query);
	        rs.next();
	        String first = rs.getString("city");
	        	        
	    	PrintWriter out = response.getWriter();
	    	response.setContentType("text/html");
	    	out.print("<center><h1>"+first+"</h1></center>");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
