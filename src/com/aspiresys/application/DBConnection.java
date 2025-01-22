package com.aspiresys.application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class DBConnection{
	    public static Connection getConnection() throws SQLException {
	        try {
	            // Load and register the JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            // Establish connection
	            return DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/food_delivery_app", "root", "password");
	        } catch (ClassNotFoundException | SQLException e) {
	            throw new SQLException("Database connection failed: " + e.getMessage());
	        }
	    }
	}



