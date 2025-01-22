//package com.aspire.FoodDeliveryApplication;
//import java.util.*;
//import java.util.regex.*;
//
//    // Custom Exception
//	class FoodNotAvailabilityException extends Exception {
//	    public FoodNotAvailabilityException(String Passmessage) {
//	        super(Passmessage);
//	    }
//	}
//
//		class FoodItem {
//	    private String name;
//	    private double price;
//	    private int stockQuantity;
//
//	    public FoodItem(String name, double price, int stockQuantity) {
//	        this.name = name;
//	        this.price = price;
//	        this.stockQuantity = stockQuantity;
//	    }
//
//	    public String getName() {
//	        return name;
//	    }
//
//	    public void setName(String name) {
//	        this.name = name;
//	    }
//
//	    public double getPrice() {
//	        return price;
//	    }
//
//	    public void setPrice(double price) {
//	        this.price = price;
//	    }
//
//	    public int getStockQuantity() {
//	        return stockQuantity;
//	    }
//
//	    public void setStockQuantity(int stockQuantity) {
//	        this.stockQuantity = stockQuantity;
//	    }
//
//	    // Method to reduce stock when an item is ordered
//	    public void reduceStock(int quantity) throws FoodNotAvailabilityException {
//	        if (quantity > stockQuantity) {
//	            throw new FoodNotAvailabilityException("Insufficient stock for " + name);
//	        }
//	        stockQuantity = stockQuantity - quantity;
//	    }
//
//	    @Override
//	    public String toString() {
//	        return name + " - $" + price + " (Stock: " + stockQuantity + ")";
//	    }
//	}
//
//	
//	class Customer {
//	    private String name;
//	    private String address;
//	    private Set<Order> orderHistory;  // To set-unique values
//
//	    public Customer(String name, String address) {
//	        this.name = name;
//	        this.address = address;
//	        this.orderHistory = new HashSet<>();  // Store unique orders
//	    }
//
//	    // Getter and Setter methods
//	    public String getName() {
//	        return name;
//	    }
//
//	    public void setName(String name) {
//	        this.name = name;
//	    }
//
//	    public String getAddress() {
//	        return address;
//	    }
//
//	    public void setAddress(String address) {
//	        this.address = address;
//	    }
//
//	    // Method to add orders to the customer's order history
//	    public void addOrder(Order order) {
//	        orderHistory.add(order);
//	    }
//
//	    public Set<Order> getOrderHistory() {
//	        return orderHistory;
//	    }
//	}
//
//	// Order class to represent an order placed by a customer
//	class Order {
//	    private Customer customer;
//	    private List<FoodItem> foodItems;  // List of food items in the order
//	    private double totalAmount;
//
//	    public Order(Customer customer) {
//	        this.customer = customer;
//	        this.foodItems = new LinkedList<>();
//	        this.totalAmount = 0.0;
//	    }
//
//	    // Method to add food item to the order
//	    public void addFoodItem(FoodItem foodItem, int quantity) throws FoodNotAvailabilityException {
//	        foodItem.reduceStock(quantity);
//	        this.foodItems.add(foodItem);
//	        this.totalAmount += foodItem.getPrice() * quantity;
//	    }
//
//	    public void displayOrderDetails() {
//	        System.out.println("Order Details for: " + customer.getName() + " - Address: " + customer.getAddress());
//	        for (FoodItem foodItem : foodItems) {
//	            System.out.println(foodItem.getName() + " - $" + foodItem.getPrice());
//	        }
//	        System.out.println("Total Amount: $" + totalAmount);
//	    }
//
//	    public double getTotalAmount() {
//	        return totalAmount;
//	    }
//
//	    // Overriding equals and hashCode to prevent duplicate orders in order history
//	    @Override
//	    public boolean equals(Object obj) {
//	        if (this == obj) return true;
//	        if (obj == null || getClass() != obj.getClass()) return false;
//	        Order order = (Order) obj;
//	        return customer.equals(order.customer) && foodItems.equals(order.foodItems);
//	    }
//
//	    @Override
//	    public int hashCode() {
//	        return Objects.hash(customer, foodItems);
//	    }
//	}
//
//	// SwiggyApp class to manage the food menu and orders
//	class SwiggyApp {
//	    private Map<Integer, FoodItem> menu;  // Food menu (ID -> FoodItem)
//
//	    public SwiggyApp() {
//	        menu = new HashMap<>();
//	    }
//
//	    // Add food item to the menu with a unique ID
//	    public void addFoodItemToMenu(FoodItem foodItem, int id) {
//	        menu.put(id, foodItem);
//	    }
//
//	    // Display the food menu
//	    public void displayMenu() {
//	        System.out.println("Food Menu:");
//	        for (Map.Entry<Integer, FoodItem> entry : menu.entrySet()) {
//	            System.out.println(entry.getKey() + ". " + entry.getValue());
//	        }
//	    }
//
//	    // Get a food item by its ID
//	    public FoodItem getFoodItemFromMenu(int id) {
//	        return menu.get(id);
//	    }
//	}
//
//	public class Demo {
//	    // Regex patterns for validating customer inputs
//	    private static final String NAME_REGEX = "^[A-Za-z\\s]{3,50}$";  // Names should be 3-50 characters long, only alphabets and spaces
//	    private static final String ADDRESS_REGEX = "^[A-Za-z0-9,\\s]{5,100}$";  // Address should be 5-100 characters long
//	    private static final String NUMERIC_REGEX = "^[0-9]+$";  // Only numeric input
//
//	    // Method to validate input using regular expressions
//	    private static boolean isValidInput(String input, String regex) {
//	        Pattern pattern = Pattern.compile(regex);
//	        Matcher matcher = pattern.matcher(input);
//	        return matcher.matches();
//	    }
//
//	    public static void main(String[] args) {
//	        // Initialize Swiggy application
//	        SwiggyApp app = new SwiggyApp();
//
//	        // Adding sample food items to the menu with unique IDs
//	        app.addFoodItemToMenu(new FoodItem("Pizza", 12.99, 10), 1);
//	        app.addFoodItemToMenu(new FoodItem("Burger", 5.49, 20), 2);
//	        app.addFoodItemToMenu(new FoodItem("Pasta", 8.99, 15), 3);
//
//	        // Creating a customer
//	        Scanner scanner = new Scanner(System.in);
//	        String customerName = "";
//	        String customerAddress = "";
//
//	        // Validate customer name with try-catch-finally
//	        while (true) {
//	            try {
//	                System.out.print("Enter your name (only alphabets and spaces, 3-50 characters): ");
//	                customerName = scanner.nextLine();
//	                if (!isValidInput(customerName, NAME_REGEX)) {
//	                    throw new IllegalArgumentException("Invalid name. Please try again.");
//	                }
//	                break;  // Exit the loop if valid input
//	            } catch (IllegalArgumentException e) {
//	                System.out.println(e.getMessage());
//	            } finally {
//	                // Clean-up or additional logging can go here if needed
//	            }
//	        }
//
//	        // Validate customer address with try-catch-finally
//	        while (true) {
//	            try {
//	                System.out.print("Enter your address (5-100 characters): ");
//	                customerAddress = scanner.nextLine();
//	                if (!isValidInput(customerAddress, ADDRESS_REGEX)) {
//	                    throw new IllegalArgumentException("Invalid address. Please try again.");
//	                }
//	                break;  // Exit the loop if valid input
//	            } catch (IllegalArgumentException e) {
//	                System.out.println(e.getMessage());
//	            } finally {
//	                // Clean-up or additional logging can go here if needed
//	            }
//	        }
//
//	        Customer customer = new Customer(customerName, customerAddress);
//
//	        // Create a new order for the customer
//	        Order order = new Order(customer);
//
//	        // Ordering process with try-catch-finally
//	        boolean ordering = true;
//	        while (ordering) {
//	            try {
//	                app.displayMenu();
//	                System.out.print("Enter the number of the food item to add to the order (0 to finish): ");
//	                String foodChoiceInput = scanner.nextLine();
//
//	                if (isValidInput(foodChoiceInput, NUMERIC_REGEX)) {
//	                    int foodChoice = Integer.parseInt(foodChoiceInput);
//
//	                    if (foodChoice == 0) {
//	                        ordering = false;
//	                        break;
//	                    }
//
//	                    // Get the selected food item by ID
//	                    FoodItem selectedFood = app.getFoodItemFromMenu(foodChoice);
//	                    if (selectedFood == null) {
//	                        System.out.println("Invalid food choice.");
//	                        continue;
//	                    }
//
//	                    System.out.print("Enter the quantity: ");
//	                    String quantityInput = scanner.nextLine();
//	                    if (isValidInput(quantityInput, NUMERIC_REGEX)) {
//	                        int quantity = Integer.parseInt(quantityInput);
//
//	                        // Add the selected food item to the order
//	                        order.addFoodItem(selectedFood, quantity);
//	                        System.out.println(quantity + " " + selectedFood.getName() + "(s) added to your order.");
//	                    } else {
//	                        System.out.println("Invalid quantity. Please enter a valid number.");
//	                    }
//	                } else {
//	                    System.out.println("Invalid food choice. Please enter a valid number.");
//	                }
//	            } catch (FoodNotAvailabilityException e) {
//	                System.out.println(e.getMessage());
//	            } catch (NumberFormatException e) {
//	                System.out.println("Invalid number format! Please enter a valid number.");
//	            } finally {
//	                // Optional: Log information or perform other actions
//	                System.out.println("Attempted to process an order.");
//	            }
//	        }
//
//	        // Display the order summary
//	        try {
//	            System.out.println("\n--- Order Summary ---");
//	            order.displayOrderDetails();
//	            customer.addOrder(order);  // Add the order to the customer's order history
//	        } catch (Exception e) {
//	            System.out.println("Error displaying order details: " + e.getMessage());
//	        } finally {
//	            // Clean-up or final logging can go here
//	            System.out.println("Order summary displayed successfully.");
//	        }
//
//	        // Display Customer's Order History
//	        System.out.println("\n--- Order History ---");
//	        for (Order o : customer.getOrderHistory()) {
//	            o.displayOrderDetails();
//	        }
//
//	        System.out.println("Thank you for using Swiggy!");
//
//	        scanner.close();
//	    }
//	}
//
//
//
