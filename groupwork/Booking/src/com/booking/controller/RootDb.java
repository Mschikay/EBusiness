package com.booking.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.booking.model.Hotel;

import java.sql.Date;

public class RootDb {
	private static RootDb instance;
	private DataSource dataSource;
	private String jndiName = "java:comp/env/jdbc/rootdb";
	
	public static RootDb getInstance() throws Exception {
		if (instance == null) {
			instance = new RootDb();
		}
		
		return instance;
	}
	
	private RootDb() throws Exception {		
		this.dataSource = getDataSource();
	}

	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();
		DataSource theDataSource = (DataSource) context.lookup(jndiName);
		return theDataSource;
	}
	
	public Connection getConnection() throws Exception {
		Connection conn = this.dataSource.getConnection();
		return conn;	
	}
	
	private void close(Connection theConn, ResultSet theRes) {
		close(theConn, null, theRes);
	}
	
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}
			
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	
	
	public String getAvailable(Date checkinTemp, Date checkoutTemp, String addrTemp, int peopleTemp) throws Exception {

		Connection connection = null;
		ResultSet resultSet = null;
//		Hotel hotel = null;
		String res = null;
		
		try {
			connection = getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call get_available_hotel(?, ?, ?, ?)}");
			callableStatement.setDate(1, checkinTemp);
			callableStatement.setDate(2, checkoutTemp);
			callableStatement.setString(3, addrTemp);
			callableStatement.setInt(4, peopleTemp);

			resultSet = callableStatement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String addr = resultSet.getString("addr");
				String pic = resultSet.getString("pic");
				
//				hotel = new Hotel(id, name, addr, pic);
				res = name;
				System.out.println(name);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close (connection, resultSet);
		}
		return res;
	}
}
