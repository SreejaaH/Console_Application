package com.aspiresys.application;
import java.sql.*;
import java.util.Scanner;

public class CustomerClass {
    private Connection conn;
    private Scanner scanner;

    public CustomerClass(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void displayMenu() {
        try {
            System.out.println("Enter your email to register or login:");
            String email = scanner.nextLine();
            if (isEmailValid(email)) {
                System.out.println("Welcome, " + email);
                CustomerOrder order = new CustomerOrder();
                while (true) {
                    System.out.println("1. View Menu");
                    System.out.println("2. Checkout");
                    System.out.println("3. Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // consume newline

                    switch (choice) {
                        case 1:
                            viewMenu(order);
                            break;
                        case 2:
                            order.checkout();
                            return; // Exit customer menu after checkout
                        case 3:
                            return; // Exit to main menu
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }
            } else {
                System.out.println("Invalid email format!");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void viewMenu(CustomerOrder order) throws SQLException {
        String query = "SELECT * FROM menu_item";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Price: $" + rs.getDouble("price"));
            System.out.println("------------------------");
        }

        System.out.println("Enter Item ID to add to cart:");
        int itemId = scanner.nextInt();
        System.out.println("Enter Quantity:");
        int quantity = scanner.nextInt();

        String itemQuery = "SELECT * FROM menu_item WHERE id = ?";
        PreparedStatement stmt2 = conn.prepareStatement(itemQuery);
        stmt2.setInt(1, itemId);
        ResultSet itemRs = stmt2.executeQuery();

        if (itemRs.next()) {
            MenuItem item = new MenuItem(
                itemRs.getInt("id"),
                itemRs.getString("name"),
                itemRs.getString("description"),
                itemRs.getDouble("price")
            );
            order.addToCart(item, quantity);
        } else {
            System.out.println("Invalid Item ID");
        }
    }

    // Email validation using regular expression
    private boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}



