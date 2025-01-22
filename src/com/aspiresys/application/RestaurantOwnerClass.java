package com.aspiresys.application;
import java.sql.*;
import java.util.Scanner;

public class RestaurantOwnerClass {
    private Connection conn;
    private Scanner scanner;

    public RestaurantOwnerClass(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void manageMenu() {
        try {
            while (true) {
                System.out.println("Restaurant Owner Menu:");
                System.out.println("1. Add Menu Item");
                System.out.println("2. View Menu Items");
                System.out.println("3. Back");
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        addMenuItem();
                        break;
                    case 2:
                        viewMenuItems();
                        break;
                    case 3:
                        return; // Back to main menu
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void addMenuItem() throws SQLException {
        System.out.println("Enter Menu Item Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Menu Item Description:");
        String description = scanner.nextLine();
        System.out.println("Enter Menu Item Price:");
        double price = scanner.nextDouble();
        scanner.nextLine();  // consume newline

        String query = "INSERT INTO menu_item (name, description, price, restaurant_owner_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, description);
        stmt.setDouble(3, price);
        stmt.setInt(4, 1);  // Assuming restaurant owner with ID 1
        stmt.executeUpdate();

        System.out.println("Menu Item Added Successfully!");
    }

    private void viewMenuItems() throws SQLException {
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



