package com.booking.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.booking.model.*;

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

	public List <Hotel> getAvailableHotel(Date checkinTemp, Date checkoutTemp, String addrTemp, int peopleTemp) throws Exception {

		Connection connection = null;
		ResultSet resultSet = null;
		List <Hotel> hotels = new ArrayList<>();

		try {
			connection = getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call get_available_hotel(?, ?, ?, ?)}");
			callableStatement.setDate(1, checkinTemp);
			callableStatement.setDate(2, checkoutTemp);
			callableStatement.setString(3, addrTemp);
			callableStatement.setInt(4, peopleTemp);

			resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String addr = resultSet.getString("addr");
				String pic = resultSet.getString("pic");
				
				Hotel hotel = new Hotel(id, name, addr, pic);
				hotels.add(hotel);
				
				System.out.println(name);
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close (connection, resultSet);
		}
		return hotels;
	}
	
	public List <RestRoom> getAvailableRoom(Date checkinTemp, Date checkoutTemp, int idTemp) throws Exception {

		Connection connection = null;
		ResultSet resultSet = null;
		List <RestRoom> rooms = new ArrayList<>();

		try {
			connection = getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call get_available_roomtype(?, ?, ?)}");
			callableStatement.setInt(1, idTemp);
			callableStatement.setDate(2, checkinTemp);
			callableStatement.setDate(3, checkoutTemp);

			resultSet = callableStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String roomType = resultSet.getString("name");
				int rest = resultSet.getInt("rest");
				double price = resultSet.getDouble("price");
				
				RestRoom room= new RestRoom(id, roomType, rest, price);
				rooms.add(room);				
			}
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close (connection, resultSet);
		}
		return rooms;
	}
	
	public void insertOrder(int hotelId, int roomtype, Date checkin, Date checkout, String fname, String lname) {
//	insertOrder (in hotelid_temp int, in roomtype_temp int, in checkin_temp date, in checkout_temp date, in fname_temp varchar(30), in lname_temp varchar(30))
		Connection connection = null;
		ResultSet resultSet = null;
		
		try {
			connection = getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call insertOrder(?, ?, ?, ?, ?, ?)}");
			callableStatement.setInt(1, hotelId);
			callableStatement.setInt(2, roomtype);
			callableStatement.setDate(3, checkin);
			callableStatement.setDate(4, checkout);
			callableStatement.setString(5, fname);
			callableStatement.setString(6, lname);

			callableStatement.executeUpdate();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			close (connection, resultSet);
		}
		return;
		
	}
}
