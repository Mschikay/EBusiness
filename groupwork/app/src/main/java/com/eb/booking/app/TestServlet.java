package com.eb.booking.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	@Resource(name = "jdbc/rootdb")
	private DataSource dataSource;
	@Resource(name = "jdbc/bank")
	private DataSource dataSource1;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn = null;
		Connection myConn1 = null;
		Statement myStmt = null;		
		Statement myStmt1 = null;
		boolean myRs;

		try {
			myConn = dataSource.getConnection();
			String sql = "drop table location;";
			myStmt = myConn.createStatement();
			myRs = myStmt.execute(sql);
			
			myConn1 = dataSource.getConnection();
			myStmt1 = myConn.createStatement();
			myRs = myStmt1.execute(sql);

		} catch (Exception exc) {
			exc.printStackTrace();
			out.println(exc.getMessage());
		}
	}

}
