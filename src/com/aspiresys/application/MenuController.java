package com.aspiresys.application;
import java.sql.Connection;
import java.util.Scanner;

	public class MenuController {
	    private Connection conn;
	    private Scanner scanner;

	    public MenuController(Connection conn, Scanner scanner) {
	        this.conn = conn;
	        this.scanner = scanner;
	    }

	    public void startMenu() {
	        while (true) {
	            System.out.println("Welcome to the Food Delivery App!");
	            System.out.println("Select Role: ");
	            System.out.println("1. Customer");
	            System.out.println("2. Restaurant Owner");
	            System.out.println("3. Admin");
	            System.out.println("4. Exit");

	            int role = scanner.nextInt();
	            scanner.nextLine();  // consume newline

	            switch (role) {
	                case 1:
	                    Customer customer = new Customer(conn, scanner);
	                    customer.displayMenu();
	                    break;
	                case 2:
	                    RestaurantOwner restaurantOwner = new RestaurantOwner(conn, scanner);
	                    restaurantOwner.manageMenu();
	                    break;
	                case 3:
	                    Admin admin = new Admin(conn, scanner);
	                    admin.viewDetails();
	                    break;
	                case 4:
	                    System.out.println("Exiting...");
	                    return;
	                default:
	                    System.out.println("Invalid choice. Try again.");
	            }
	        }
	    }
	}
	}
