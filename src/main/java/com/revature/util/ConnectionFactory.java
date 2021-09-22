package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String URL = "jdbc:postgresql://database-1.crtqdgrkyvxb.us-east-2.rds.amazonaws.com/";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Pa$$w0rd";

	public Connection getConnection() throws SQLException {
		
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		return conn;
		
		
	}

}
