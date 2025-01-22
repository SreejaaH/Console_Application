package com.aspiresys.fooddemo;

import java.sql.*;
import java.util.*;

public class FoodDemo {

    public static void main(String[] args) {
        try {
            Connection connectionObject = DatabaseConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            MenuController menuController = new MenuController(connectionObject, scanner);
            menuController.startMenu(); // Start the application menu flow
        } catch (SQLException message) {
            System.err.println("Error: " + message.getMessage());
        }
    }
}

class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/fooddeliveryapp";
        final String username = "root";
        final String password = "Aspire@123"; 
        
        return DriverManager.getConnection(url, username, password);
    }
}

class MenuController {
    private Connection connectionObject;
    private Scanner scanner;

    public MenuController(Connection connectionObject, Scanner scanner) {
        this.connectionObject = connectionObject;
        this.scanner = scanner;
    }

    public void startMenu() {
        while (true) {
            System.out.println("Welcome to the Online Food Delivery App!");
            System.out.println("Please select the role from the below options: ");
            System.out.println("1. Customer");
            System.out.println("2. Restaurant Owner");
            System.out.println("3. Admin");
            System.out.println("4. Exit");

            int role = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (role) {
                case 1:
                    Customer customer = new Customer(connectionObject, scanner);
                    customer.displayMenu();
                    break;
                case 2:
                    RestaurantOwner restaurantOwner = new RestaurantOwner(connectionObject, scanner);
                    restaurantOwner.manageMenu();
                    break;
                case 3:
                    Admin admin = new Admin(connectionObject, scanner);
                    admin.viewDetails();
                    break;
                case 4:
                    System.out.println("Exiting Thank you!..."); 
                    return;
                default:
                    System.out.println("Invalid choice. Please Try again.");
            }
        }
    }
}

class Customer {
    private Connection connectionObject;
    private Scanner scanner;

    public Customer(Connection connObject, Scanner scanner) {
        this.connectionObject = connObject;
        this.scanner = scanner;
    }

    public void displayMenu() {
        try {
            System.out.println("Enter your email to register or log in:");
            String email = scanner.nextLine();

            if (isEmailValid(email)) {
                if (!isEmailExist(email)) {
                    // Collect customer name for registration
                    System.out.println("Enter your name:");
                    String name = scanner.nextLine();
                    saveCustomerDetails(name, email);

                    System.out.println("Welcome, " + name);
                } else {
                    System.out.println("Email already exists. Proceeding with login...");
                }

                CustomerOrder order = new CustomerOrder();
                while (true) {
                    // Update menu
                    System.out.println("1. View Restaurants");
                    System.out.println("2. View Menu");
                    System.out.println("3. Checkout after selecting menu");
                    System.out.println("4. Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    switch (choice) {
                        case 1:
                            viewRestaurants();  // View restaurant details
                            break;
                        case 2:
                            viewMenu(order);  // View the menu items
                            break;
                        case 3:
                            order.checkout();  // Proceed with checkout
                            return;  // Exit customer menu after checkout
                        case 4:
                            return;  // Exit to main menu
                        default:
                            System.out.println("Invalid choice. Please Try again.");
                    }
                }
            } else {
                System.out.println("Invalid email format! Please enter a valid one.");
            }
        } catch (SQLException message) {
            System.err.println("Error: " + message.getMessage());
        }
    }

    private boolean isEmailExist(String email) throws SQLException {
        String query = "SELECT * FROM customer WHERE email = ?";
        PreparedStatement statement = connectionObject.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();
    }

    private void saveCustomerDetails(String name, String email) throws SQLException {
        String query = "INSERT INTO customer (name, email) VALUES (?, ?)";
        PreparedStatement statement = connectionObject.prepareStatement(query);
        statement.setString(1, name);
        statement.setString(2, email);
        statement.executeUpdate();
        System.out.println("Customer registered successfully.");
    }

    private void viewMenu(CustomerOrder order) throws SQLException {
        String query = "SELECT * FROM menu_item";
        Statement statement = connectionObject.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        boolean hasItems = false;
        while (resultSet.next()) {
            hasItems = true;
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Description: " + resultSet.getString("description"));
            System.out.println("Price: $" + resultSet.getDouble("price"));
            System.out.println("------------------------");
        }

        if (!hasItems) {
            System.out.println("Sorry! No menu items available at the moment.");
        } else {
            System.out.println("Enter Item ID to add to cart:");
            int itemId = scanner.nextInt();
            System.out.println("Enter Quantity:");
            int quantity = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            String itemQuery = "SELECT * FROM menu_item WHERE id = ?";
            PreparedStatement statement1 = connectionObject.prepareStatement(itemQuery);
            statement1.setInt(1, itemId);
            ResultSet items = statement1.executeQuery();

            if (items.next()) {
                MenuItem item = new MenuItem(
                    items.getInt("id"),
                    items.getString("name"),
                    items.getString("description"),
                    items.getDouble("price")
                );
                order.addToCart(item, quantity);

                // Optionally, ask for rating and review
                System.out.println("Do you want to rate and review this item? (Yes/No)");
                String reviewChoice = scanner.nextLine();
                if ("Yes".equalsIgnoreCase(reviewChoice)) {
                    System.out.println("Enter your rating (1-5):");
                    int rating = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.println("Enter your experience:");
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

    private void viewRestaurants() throws SQLException {
        String query = "SELECT * FROM restaurant_owner";
        Statement statement2 = connectionObject.createStatement();
        ResultSet resultSet = statement2.executeQuery(query);

        boolean hasRestaurants = false;
        while (resultSet.next()) {
            hasRestaurants = true;
            System.out.println("Restaurant Name: " + resultSet.getString("restaurant_name"));
            System.out.println("Location: " + resultSet.getString("location"));
            System.out.println("------------------------");
        }

        if (!hasRestaurants) {
            System.out.println("Sorry! No restaurants available at the moment.");
        }
    }
}

class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;
    private List<String> reviews = new ArrayList<>();
    private List<Integer> ratings = new ArrayList<>();

    public MenuItem(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public void addReview(int rating, String review) {
        this.reviews.add(review);
        this.ratings.add(rating);
        System.out.println("Review Added: " + review);
    }

    public double getAverageRating() {
        int total = 0;
        for (int rating : ratings) {
            total = total + rating;
        }

        if (ratings.isEmpty()) {
            return 0;
        } else {
            return (double) total / ratings.size();
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class CustomerOrder {
    private List<MenuItem> cart = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();

    public void addToCart(MenuItem item, int quantity) {
        cart.add(item);
        quantities.add(quantity);
        System.out.println(item.getName() + " added to cart.");
    }

    public void checkout() {
        double totalPrice = 0;
        System.out.println("Items in your cart:");
        for (int i = 0; i < cart.size(); i++) {
            MenuItem item = cart.get(i);
            int quantity = quantities.get(i);
            double itemTotal = item.getPrice() * quantity;
            totalPrice += itemTotal;
            System.out.println(item.getName() + " x" + quantity + " = $" + itemTotal);
            System.out.println("Average Rating: " + item.getAverageRating() + " stars");
        }
        System.out.println("Total Price: $" + totalPrice);

        // Confirm order
        System.out.println("Order Confirmed. The restaurant is now preparing your order.");
    }
}

class RestaurantOwner {
    private Connection connectionObject;
    private Scanner scanner;

    public RestaurantOwner(Connection connectionObject, Scanner scanner) {
        this.connectionObject = connectionObject;
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
        } catch (SQLException message) {
            System.err.println("Error: " + message.getMessage());
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
        String location = scanner.nextLine();  

        // Insert restaurant owner details into the database
        String query = "INSERT INTO restaurant_owner (restaurant_name, name, email, phone_number, location) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement3 = connectionObject.prepareStatement(query);
        statement3.setString(1, restaurantName);
        statement3.setString(2, ownerName);
        statement3.setString(3, email);
        statement3.setString(4, phoneNumber);
        statement3.setString(5, location);
        statement3.executeUpdate();

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

        // Assuming that the restaurant owner ID is retrieved dynamically after owner login
        int restaurantOwnerId = 1; // Example: Restaurant owner with ID 1. This should ideally be fetched dynamically.

        String query = "INSERT INTO menu_item (name, description, price, restaurant_owner_id) VALUES (?, ?, ?, ?)";
        PreparedStatement statement4 = connectionObject.prepareStatement(query);
        statement4.setString(1, name);
        statement4.setString(2, description);
        statement4.setDouble(3, price);
        statement4.setInt(4, restaurantOwnerId);
        statement4.executeUpdate();

        System.out.println("Menu Item Added Successfully!");
    }

    private void viewMenuItems() throws SQLException {
        String query = "SELECT * FROM menu_item";
        Statement statement5 = connectionObject.createStatement();
        ResultSet resultSet = statement5.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Price: $" + resultSet.getDouble("price"));
            System.out.println("------------------------");
        }
    }
}

class Admin {
    private Connection connectionObject;
    private Scanner scanner;

    public Admin(Connection connectionObject, Scanner scanner) {
        this.connectionObject = connectionObject;
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
        } catch (SQLException message) {
            System.err.println("Error: " + message.getMessage());
        }
    }

    private void viewAllCustomers() throws SQLException {
        String query = "SELECT * FROM customer";
        Statement statement6 = connectionObject.createStatement();
        ResultSet resultSet = statement6.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Email: " + resultSet.getString("email"));
            System.out.println("------------------------");
        }
    }

    private void viewAllRestaurantOwners() throws SQLException {
        String query = "SELECT * FROM restaurant_owner";
        Statement statement7 = connectionObject.createStatement();
        ResultSet resultSet = statement7.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Restaurant Name: " + resultSet.getString("restaurant_name"));
            System.out.println("Owner Name: " + resultSet.getString("name"));
            System.out.println("Email: " + resultSet.getString("email"));
            System.out.println("Phone: " + resultSet.getString("phone_number"));
            System.out.println("Location: " + resultSet.getString("location"));
            System.out.println("------------------------");
        }
    }

    private void viewAllMenuItems() throws SQLException {
        String query = "SELECT * FROM menu_item";
        Statement statement8 = connectionObject.createStatement();
        ResultSet resultSet = statement8.executeQuery(query);

        while (resultSet.next()) {
            System.out.println("ID: " + resultSet.getInt("id"));
            System.out.println("Name: " + resultSet.getString("name"));
            System.out.println("Description: " + resultSet.getString("description"));
            System.out.println("Price: $" + resultSet.getDouble("price"));
            System.out.println("------------------------");
        }
    }
}


class CustomerOrders {
  public void checkout() {
      System.out.println("Checkout complete. Thank you for ordering!");
  }
}
