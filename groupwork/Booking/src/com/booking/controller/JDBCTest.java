package com.booking.controller;

import java.sql.*;

public class JDBCTest {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/rootdb?useSSL=false&serverTimezone=UTC";

	//  Database credentials
	static final String USER = "ziqi";
	static final String PASS = "123456";
	   
	public static void main(String[] args) {
		Connection conn = null;
	    Statement stmt = null;
	    try{
	    	//STEP 2: Register JDBC driver
	    	Class.forName("com.mysql.jdbc.Driver");
	      	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      	stmt = conn.createStatement();
	        String query = "select * from location";
	        ResultSet rs = stmt.executeQuery(query);
	        rs.next();
	        String first = rs.getString("city");
	        System.out.println(first);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
}
