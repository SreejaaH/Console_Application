package com.aspiresys.FoodApplication;
import java.sql.*;
import java.util.*;

public class FoodDelivery {
    private Connection conn;
    private Scanner scanner;

    public FoodDelivery(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void displayMenu() {
        try {
            System.out.println("Enter your email to register or login:");
            String email = scanner.nextLine();
            
            if (isEmailValid(email)) {
                if (!isEmailExist(email)) {
                    // Collect customer name and email for registration
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    
                    // Save customer details in the database
                    saveCustomerDetails(name, email);

                    System.out.println("Welcome, " + name);
                } else {
                    System.out.println("Email already exists. Proceeding with login...");
                }

                CustomerOrder order = new CustomerOrder();
                while (true) {
                    System.out.println("1. View Restaurants by Location");
                    System.out.println("2. Checkout");
                    System.out.println("3. Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    switch (choice) {
                        case 1:
                            viewRestaurantsByLocation();
                            break;
                        case 2:
                            order.checkout();
                            return;  // Exit customer menu after checkout
                        case 3:
                            return;  // Exit to main menu
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

    private boolean isEmailExist(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    private void saveCustomerDetails(String name, String email) throws SQLException {
        String query = "INSERT INTO customer (name, email) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, email);
        stmt.executeUpdate();
        System.out.println("Customer registered successfully.");
    }

    private void viewRestaurantsByLocation() throws SQLException {
        System.out.println("Enter Location to view restaurants:");
        String location = scanner.nextLine();

        String query = "SELECT * FROM restaurant_owner WHERE location = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, location);
        ResultSet rs = stmt.executeQuery();

        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println("Restaurant Name: " + rs.getString("restaurant_name"));
            System.out.println("Owner Name: " + rs.getString("name"));
            System.out.println("Location: " + rs.getString("location"));
            System.out.println("------------------------");
        }

        if (!found) {
            System.out.println("No restaurants found in the location: " + location);
        } else {
            // Optionally, allow customers to view the menu of the restaurant
            System.out.println("Enter Restaurant Name to view the menu or type 'back' to go back:");
            String restaurantName = scanner.nextLine();
            if (!restaurantName.equalsIgnoreCase("back")) {
                viewMenu(restaurantName);
            }
        }
    }

    private void viewMenu(String restaurantName) throws SQLException {
        String query = "SELECT * FROM menu_item WHERE restaurant_name = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, restaurantName);
        ResultSet rs = stmt.executeQuery();

        boolean hasItems = false;
        while (rs.next()) {
            hasItems = true;
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Price: $" + rs.getDouble("price"));
            System.out.println("------------------------");
        }

        if (!hasItems) {
            System.out.println("No menu items available at the moment.");
        } else {
            System.out.println("Enter Item ID to add to cart:");
            int itemId = scanner.nextInt();
            System.out.println("Enter Quantity:");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline

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
                // Assuming CustomerOrder has a method to add items to the cart
                CustomerOrder order = new CustomerOrder();
                order.addToCart(item, quantity);

                // Optionally, ask for rating and review
                System.out.println("Do you want to rate and review this item? (Y/N)");
                String reviewChoice = scanner.nextLine();
                if ("Y".equalsIgnoreCase(reviewChoice)) {
                    System.out.println("Enter your rating (1-5):");
                    int rating = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter your review:");
                    String review = scanner.nextLine();
                    item.addReview(rating, review);
                }
            } else {
                System.out.println("Invalid Item ID");
            }
        }
    }

    private boolean isEmailValid(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}

class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;

    public MenuItem(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void addReview(int rating, String review) {
        System.out.println("Thank you for your review!");
        // Code to save review and rating to the database could be added here
    }
}

class CustomerOrder {
    private List<MenuItem> cart = new ArrayList<>();
    private Map<MenuItem, Integer> quantities = new HashMap<>();

    public void addToCart(MenuItem item, int quantity) {
        cart.add(item);
        quantities.put(item, quantity);
    }

    public void checkout() {
        System.out.println("Checking out...");
        double total = 0;
        for (MenuItem item : cart) {
            int quantity = quantities.get(item);
            total += item.getPrice() * quantity;
            System.out.println(item.getName() + " x" + quantity + ": $" + (item.getPrice() * quantity));
        }
        System.out.println("Total: $" + total);
        System.out.println("Thank you for your order!");
    }
}

class RestaurantOwner {
    private Connection conn;
    private Scanner scanner;

    public RestaurantOwner(Connection conn, Scanner scanner) {
        this.conn = conn;
        this.scanner = scanner;
    }

    public void manageMenu() {
        try {
            while (true) {
                System.out.println("Restaurant Owner Menu:");
                System.out.println("1. Register Restaurant");
                System.out.println("2. Add Menu Item");
                System.out.println("3. View Menu Items");
                System.out.println("4. Back");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        registerRestaurant();
                        break;
                    case 2:
                        addMenuItem();
                        break;
                    case 3:
                        viewMenuItems();
                        break;
                    case 4:
                        return;  // Back to main menu
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void registerRestaurant() throws SQLException {
        System.out.println("Enter Restaurant Name:");
        String restaurantName = scanner.nextLine();

        System.out.println("Enter Owner's Name:");
        String ownerName = scanner.nextLine();

        System.out.println("Enter Owner's Phone Number:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter Owner's Email:");
        String email = scanner.nextLine();

        System.out.println("Enter Restaurant Location:");
        String location = scanner.nextLine();  // Get the location from the owner

        // Insert restaurant owner details into the database
        String query = "INSERT INTO restaurant_owner (restaurant_name, name, email, phone_number, location) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, restaurantName);
        stmt.setString(2, ownerName);
        stmt.setString(3, email);
        stmt.setString(4, phoneNumber);
        stmt.setString(5, location);
        stmt.executeUpdate();

        System.out.println("Restaurant Registered Successfully!");
    }

    private void addMenuItem() throws SQLException {
        System.out.println("Enter Menu Item Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Menu Item Description:");
        String description = scanner.nextLine();
        System.out.println("Enter Menu Item Price:");
        double price = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        // Assuming that the restaurant owner ID is stored after the owner is registered
        int restaurantOwnerId = 1; // Example: Restaurant owner with ID 1. This should ideally be fetched dynamically.

        String query = "INSERT INTO menu_item (name, description, price, restaurant_owner_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, description);
        stmt.setDouble(3, price);
        stmt.setInt(4, restaurantOwnerId);
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
            System.out.println("Price: $" + rs.getDouble("price"));
            System.out.println("------------------------");
        }
    }
}

class Admin {
    private Connection conn;
    private Scanner scanner;

    public Admin(Connection conn, Scanner scanner) {
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
                scanner.nextLine();  // Consume newline

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
                        return;  // Back to main menu
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
            System.out.println("Owner Name: " + rs.getString("name"));
            System.out.println("Location: " + rs.getString("location"));
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
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Price: $" + rs.getDouble("price"));
            System.out.println("------------------------");
        }
    }
}

class CustomerOrders {
    public void checkout() {
        System.out.println("Checkout complete. Thank you for ordering!");
   }
}

