package com.aspiresys.fooddemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class JdbcConnection {
	    public static Connection getConnection() throws SQLException {
	        final String url = "jdbc:mysql://localhost:3306/fooddeliveryapp";
	        final String username = "root";
	        final String password = "Aspire@123"; // Use your actual database password
	        
	        // Return the database connection
	        return DriverManager.getConnection(url, username, password);
	    }
	}


