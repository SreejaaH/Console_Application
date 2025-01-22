package com.aspiresys.application;
import java.sql.*;
import java.util.Scanner;

	public class AdminClass{
	    private Connection conn;
	    private Scanner scanner;

	    public AdminClass(Connection conn, Scanner scanner) {
	        this.conn = conn;
	        this.scanner = scanner;
	    }

	    public void viewDetails() {
	        try {
	            while (true) {
	                System.out.println("Admin Menu:");
	                System.out.println("1. View All Customers");
	                System.out.println("2. View All Restaurant Owners");
	                System.out.println("3. View All Menu Items");
	                System.out.println("4. Back");
	                int choice = scanner.nextInt();
	                scanner.nextLine();  // consume newline

	                switch (choice) {
	                    case 1:
	                        viewAllCustomers();
	                        break;
	                    case 2:
	                        viewAllRestaurantOwners();
	                        break;
	                    case 3:
	                        viewAllMenuItems();
	                        break;
	                    case 4:
	                        return; // Back to main menu
	                    default:
	                        System.out.println("Invalid choice. Try again.");
	                }
	            }
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	        }
	    }

	    private void viewAllCustomers() throws SQLException {
	        String query = "SELECT * FROM customer";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("Email: " + rs.getString("email"));
	            System.out.println("------------------------");
	        }
	    }

	    private void viewAllRestaurantOwners() throws SQLException {
	        String query = "SELECT * FROM restaurant_owner";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Restaurant Name: " + rs.getString("restaurant_name"));
	            System.out.println("Email: " + rs.getString("email"));
	            System.out.println("------------------------");
	        }
	    }

	    private void viewAllMenuItems() throws SQLException {
	        String query = "SELECT * FROM menu_item";
	        Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            System.out.println("ID: " + rs.getInt("id"));
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("Price: " + rs.getDouble("price"));
	            System.out.println("------------------------");
	        }
	    }
	}


