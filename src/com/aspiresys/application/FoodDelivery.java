//package com.aspiresys.application;
//
//import java.util.*;
//
////Customer Class
//class Customer {
// private String name;
// private String email;
// private String phoneNumber;
// private String address;  // Added customer address
// private Cart cart;
// private List<Review> reviews;
//
// public Customer(String name, String email, String phoneNumber, String address) {
//     this.name = name;
//     this.email = email;
//     this.phoneNumber = phoneNumber;
//     this.address = address;
//     this.cart = new Cart();
//     this.reviews = new ArrayList<>();
// }
//
// // Add product to cart
// public void addToCart(MenuItem item, int quantity) {
//     cart.addItem(item, quantity);
// }
//
// // Checkout and place an order
// public Order checkout(Restaurant restaurant) {
//     Order order = new Order(restaurant, cart, this);  // Pass customer object to order
//     cart.clearCart();
//     return order;
// }
//
// // Add review
// public void addReview(Review review) {
//     reviews.add(review);
// }
//
// // Getters
// public String getName() {
//     return name;
// }
//
// public Cart getCart() {
//     return cart;
// }
//
// public List<Review> getReviews() {
//     return reviews;
// }
//
// public String getEmail() {
//     return email;
// }
//
// public String getPhoneNumber() {
//     return phoneNumber;
// }
//
// public String getAddress() {
//     return address;  // New method to get customer address
// }
//}
//
////Cart Class
//class Cart {
// private Map<MenuItem, Integer> cartItems;
//
// public Cart() {
//     cartItems = new HashMap<>();
// }
//
// public void addItem(MenuItem item, int quantity) {
//     cartItems.put(item, quantity);
// }
//
// public void clearCart() {
//     cartItems.clear();
// }
//
// public Map<MenuItem, Integer> getCartItems() {
//     return cartItems;
// }
//
// public double getTotalPrice() {
//     double total = 0.0;
//     for (Map.Entry<MenuItem, Integer> entry : cartItems.entrySet()) {
//         total += entry.getKey().getPrice() * entry.getValue();
//     }
//     return total;
// }
//}
//
////MenuItem Class with Category and Stock Availability
//class MenuItem {
// private String name;
// private double price;
// private String description;
// private String category;  // Food category (e.g., Appetizer, Main Course, Dessert)
// private int stock;  // Track stock availability
//
// public MenuItem(String name, double price, String description, String category, int stock) {
//     this.name = name;
//     this.price = price;
//     this.description = description;
//     this.category = category;
//     this.stock = stock;
// }
//
// // Set stock
// public void setStock(int stock) {
//     this.stock = stock;
// }
//
// // Getters
// public String getName() {
//     return name;
// }
//
// public double getPrice() {
//     return price;
// }
//
// public String getDescription() {
//     return description;
// }
//
// public String getCategory() {
//     return category;
// }
//
// public int getStock() {
//     return stock;
// }
//
// @Override
// public String toString() {
//     return name + " - $" + price + ": " + description + " (Category: " + category + ", Stock: " + stock + ")";
// }
//}
//
////Order Class with Payment Status and Transaction History
//class Order {
// private Restaurant restaurant;
// private Cart cart;
// private Customer customer;  // Added customer reference
// private String orderStatus;
// private Date orderDate;
// private boolean isPaid;
// private String transactionId;
//
// public Order(Restaurant restaurant, Cart cart, Customer customer) {
//     this.restaurant = restaurant;
//     this.cart = cart;
//     this.customer = customer;
//     this.orderStatus = "Pending";
//     this.orderDate = new Date();
//     this.isPaid = false;
//     this.transactionId = generateTransactionId();
// }
//
// public void markAsPaid() {
//     this.isPaid = true;
// }
//
// private String generateTransactionId() {
//     return "TXN" + new Random().nextInt(10000);
// }
//
// public String getOrderStatus() {
//     return orderStatus;
// }
//
// public Date getOrderDate() {
//     return orderDate;
// }
//
// public boolean isPaid() {
//     return isPaid;
// }
//
// public String getTransactionId() {
//     return transactionId;
// }
//
// public Cart getCart() {
//     return cart;
// }
//
// public Customer getCustomer() {
//     return customer;
// }
//
// @Override
// public String toString() {
//     return "Order from " + restaurant.getName() + " - Status: " + orderStatus + ", Total: $" + cart.getTotalPrice() + ", Paid: " + (isPaid ? "Yes" : "No") + ", Transaction ID: " + transactionId + ", Date: " + orderDate;
// }
//}
//
////Review Class
//class Review {
// private String reviewText;
// private int rating;
//
// public Review(String reviewText, int rating) {
//     this.reviewText = reviewText;
//     this.rating = rating;
// }
//
// public String getReviewText() {
//     return reviewText;
// }
//
// public int getRating() {
//     return rating;
// }
//
// @Override
// public String toString() {
//     return "Rating: " + rating + "/5, Review: " + reviewText;
// }
//}
//
////Restaurant Class
//class Restaurant {
// private String name;
// private String location;
// private String contact;
// private List<MenuItem> menu;
// private List<Order> orders;
// private List<Review> reviews;
//
// public Restaurant(String name, String location, String contact) {
//     this.name = name;
//     this.location = location;
//     this.contact = contact;
//     this.menu = new ArrayList<>();
//     this.orders = new ArrayList<>();
//     this.reviews = new ArrayList<>();
// }
//
// public void addMenuItem(MenuItem item) {
//     menu.add(item);
// }
//
// public void editMenuItem(MenuItem item, String name, double price, String description, String category, int stock) {
//     item.setStock(stock);
// }
//
// public void removeMenuItem(MenuItem item) {
//     menu.remove(item);
// }
//
// public List<MenuItem> getMenu() {
//     return menu;
// }
//
// public void addOrder(Order order) {
//     orders.add(order);
// }
//
// public void trackOrderPaymentStatus(Order order) {
//     System.out.println("Order ID: " + order.getTransactionId() + " - Payment Status: " + (order.isPaid() ? "Paid" : "Not Paid"));
// }
//
// public void updateStock(MenuItem item, int stock) {
//     item.setStock(stock);
// }
//
// public String getName() {
//     return name;
// }
//
// public String getLocation() {
//     return location;
// }
//
// public String getContact() {
//     return contact;
// }
//}
//
////DeliveryPartner Class
//class DeliveryPartner {
// private String name;
// private String contact;
// private List<Order> assignedOrders;
// private Map<Order, String> deliveryStatus;
//
// public DeliveryPartner(String name, String contact) {
//     this.name = name;
//     this.contact = contact;
//     this.assignedOrders = new ArrayList<>();
//     this.deliveryStatus = new HashMap<>();
// }
//
// public void acceptOrder(Order order) {
//     assignedOrders.add(order);
//     deliveryStatus.put(order, "Accepted");
// }
//
// public void markDeliveryStatus(Order order, String status) {
//     deliveryStatus.put(order, status);
// }
//
// public void rateRestaurant(Restaurant restaurant, int rating) {
//     System.out.println("Delivery Partner rated restaurant " + restaurant.getName() + ": " + rating + "/5");
// }
//
// public void rateCustomer(Customer customer, int rating) {
//     System.out.println("Delivery Partner rated customer " + customer.getName() + ": " + rating + "/5");
// }
//
// public void viewAssignedOrders() {
//     for (Order order : assignedOrders) {
//         System.out.println("Assigned Order: " + order);
//     }
// }
//
// public String getName() {
//     return name;
// }
//
// public String getContact() {
//     return contact;
// }
//}
//
////Admin Class
//class Admin {
// private Map<String, DeliveryPartner> deliveryPartners;
// private Map<String, RestaurantOwner> restaurantOwners;
//
// public Admin() {
//     deliveryPartners = new HashMap<>();
//     restaurantOwners = new HashMap<>();
// }
//
// public void registerDeliveryPartner(String name, DeliveryPartner partner) {
//     deliveryPartners.put(name, partner);
// }
//
// public void registerRestaurantOwner(String name, RestaurantOwner owner) {
//     restaurantOwners.put(name, owner);
// }
//
// public void viewDeliveryPartner(String name) {
//     DeliveryPartner partner = deliveryPartners.get(name);
//     if (partner != null) {
//         System.out.println("Delivery Partner: " + partner.getName() + ", Contact: " + partner.getContact());
//     } else {
//         System.out.println("Delivery Partner not found.");
//     }
// }
//
// public void assignOrderToPartner(String partnerName, Order order) {
//     DeliveryPartner partner = deliveryPartners.get(partnerName);
//     if (partner != null) {
//         partner.acceptOrder(order);
//     } else {
//         System.out.println("Delivery partner not found.");
//     }
// }
//}
//
////RestaurantOwner Class
//class RestaurantOwner {
// private String name;
// private String contact;
// private Restaurant restaurant;
//
// public RestaurantOwner(String name, String contact, Restaurant restaurant) {
//     this.name = name;
//     this.contact = contact;
//     this.restaurant = restaurant;
// }
//
// public void addNewMenuItem(String name, double price, String description, String category, int stock) {
//     MenuItem newItem = new MenuItem(name, price, description, category, stock);
//     restaurant.addMenuItem(newItem);
// }
//
// public void removeMenuItem(MenuItem item) {
//     restaurant.removeMenuItem(item);
// }
//
// public Restaurant getRestaurant() {
//     return restaurant;
// }
//}
//
////Main Class
//public class FoodDelivery {
//
// public static void main(String[] args) {
//     Scanner scanner = new Scanner(System.in);
//
//     // Create Admin
//     Admin admin = new Admin();
//
//     // Create Restaurant Owner and register
//     Restaurant pizzaPlace = new Restaurant("Pizza Place", "New York", "123-456-7890");
//     RestaurantOwner pizzaOwner = new RestaurantOwner("Alice", "987-654-3210", pizzaPlace);
//     admin.registerRestaurantOwner(pizzaOwner.getRestaurant().getName(), pizzaOwner);
//
//     // Register delivery partners
//     System.out.println("Register delivery partner:");
//     System.out.println("Enter partner name:");
//     String partnerName = scanner.nextLine();
//     System.out.println("Enter partner contact:");
//     String partnerContact = scanner.nextLine();
//     DeliveryPartner deliveryPartner = new DeliveryPartner(partnerName, partnerContact);
//     admin.registerDeliveryPartner(partnerName, deliveryPartner);
//
//     // Create menu items for pizza place
//     pizzaOwner.addNewMenuItem("Cheese Pizza", 9.99, "Delicious cheese pizza", "Main Course", 100);
//     pizzaOwner.addNewMenuItem("Pepperoni Pizza", 11.99, "Pizza with pepperoni", "Main Course", 50);
//
//     // Create customer
//     System.out.println("Enter your name:");
//     String name = scanner.nextLine();
//     System.out.println("Enter your email:");
//     String email = scanner.nextLine();
//     System.out.println("Enter your phone number:");
//     String phone = scanner.nextLine();
//     System.out.println("Enter your address:");
//     String address = scanner.nextLine();
//     Customer customer = new Customer(name, email, phone, address);
//
//     // View restaurant menu
//     System.out.println("\nAvailable Menu at Pizza Place:");
//     for (MenuItem item : pizzaPlace.getMenu()) {
//         System.out.println(item);
//     }
//
//     // Customer selects and adds to cart
//     System.out.println("\nEnter the name of the menu item you want to add to cart:");
//     String menuItemName = scanner.nextLine().trim().toLowerCase();  // Trim and make case insensitive
//     MenuItem selectedItem = null;
//
//     // Iterate over the menu items and compare case-insensitively
//     for (MenuItem item : pizzaPlace.getMenu()) {
//         if (item.getName().toLowerCase().equals(menuItemName)) {  // Convert both to lower case
//             selectedItem = item;
//             break;
//         }
//     }
//
//     if (selectedItem != null) {
//         System.out.println("Enter quantity:");
//         int quantity = scanner.nextInt();
//         customer.addToCart(selectedItem, quantity);
//         System.out.println("Added to cart: " + selectedItem.getName() + " x" + quantity);
//
//         // Checkout
//         Order order = customer.checkout(pizzaPlace);
//         pizzaPlace.addOrder(order);
//
//         // Admin assigns order to the delivery partner
//         admin.assignOrderToPartner(partnerName, order);
//
//         // Display order to delivery partner
//         System.out.println("\nAssigned Orders to " + deliveryPartner.getName() + ":");
//         deliveryPartner.viewAssignedOrders();
//
//         // Delivery partner rates restaurant and customer
//         deliveryPartner.rateRestaurant(pizzaPlace, 5);
//         deliveryPartner.rateCustomer(customer, 4);
//
//         // Mark delivery status
//         deliveryPartner.markDeliveryStatus(order, "Delivered");
//         System.out.println("\nDelivery Status: " + deliveryPartner.getName() + " - " + order.getTransactionId() + " - Delivered");
//     } else {
//         System.out.println("Menu item not found.");
//     }
// }
//}
//
///*import java.util.*;
//
////Customer Class
//class Customer {
// private String name;
// private String email;
// private String phoneNumber;
// private String address;
// private Cart cart;
// private List<Review> reviews;
// private boolean isActive; // Status for deactivation
//
// public Customer(String name, String email, String phoneNumber, String address) {
//     this.name = name;
//     this.email = email;
//     this.phoneNumber = phoneNumber;
//     this.address = address;
//     this.cart = new Cart();
//     this.reviews = new ArrayList<>();
//     this.isActive = true; // Active by default
// }
//
// public void deactivateAccount() {
//     this.isActive = false;
// }
//
// public void reactivateAccount() {
//     this.isActive = true;
// }
//
// public boolean isActive() {
//     return isActive;
// }
//
// public String getName() {
//     return name;
// }
//
// public String getEmail() {
//     return email;
// }
//
// public String getPhoneNumber() {
//     return phoneNumber;
// }
//
// public String getAddress() {
//     return address;
// }
//
// public Cart getCart() {
//     return cart;
// }
//
// public List<Review> getReviews() {
//     return reviews;
// }
//}
//
////Cart Class
//class Cart {
// private Map<MenuItem, Integer> cartItems;
//
// public Cart() {
//     cartItems = new HashMap<>();
// }
//
// public void addItem(MenuItem item, int quantity) {
//     cartItems.put(item, quantity);
// }
//
// public void clearCart() {
//     cartItems.clear();
// }
//
// public Map<MenuItem, Integer> getCartItems() {
//     return cartItems;
// }
//
// public double getTotalPrice() {
//     double total = 0.0;
//     for (Map.Entry<MenuItem, Integer> entry : cartItems.entrySet()) {
//         total += entry.getKey().getPrice() * entry.getValue();
//     }
//     return total;
// }
//}
//
////MenuItem Class
//class MenuItem {
// private String name;
// private double price;
// private String description;
// private String category;
// private int stock;
//
// public MenuItem(String name, double price, String description, String category, int stock) {
//     this.name = name;
//     this.price = price;
//     this.description = description;
//     this.category = category;
//     this.stock = stock;
// }
//
// public void setStock(int stock) {
//     this.stock = stock;
// }
//
// public String getName() {
//     return name;
// }
//
// public double getPrice() {
//     return price;
// }
//
// public String getDescription() {
//     return description;
// }
//
// public String getCategory() {
//     return category;
// }
//
// public int getStock() {
//     return stock;
// }
//
// @Override
// public String toString() {
//     return name + " - $" + price + ": " + description + " (Category: " + category + ", Stock: " + stock + ")";
// }
//}
//
////Order Class
//class Order {
// private Restaurant restaurant;
// private Cart cart;
// private Customer customer;
// private String orderStatus;
// private Date orderDate;
// private boolean isPaid;
// private String transactionId;
//
// public Order(Restaurant restaurant, Cart cart, Customer customer) {
//     this.restaurant = restaurant;
//     this.cart = cart;
//     this.customer = customer;
//     this.orderStatus = "Pending";
//     this.orderDate = new Date();
//     this.isPaid = false;
//     this.transactionId = generateTransactionId();
// }
//
// public void markAsPaid() {
//     this.isPaid = true;
// }
//
// private String generateTransactionId() {
//     return "TXN" + new Random().nextInt(10000);
// }
//
// public String getOrderStatus() {
//     return orderStatus;
// }
//
// public Date getOrderDate() {
//     return orderDate;
// }
//
// public boolean isPaid() {
//     return isPaid;
// }
//
// public String getTransactionId() {
//     return transactionId;
// }
//
// public Cart getCart() {
//     return cart;
// }
//
// public Customer getCustomer() {
//     return customer;
// }
//
// @Override
// public String toString() {
//     return "Order from " + restaurant.getName() + " - Status: " + orderStatus + ", Total: $" + cart.getTotalPrice() + ", Paid: " + (isPaid ? "Yes" : "No") + ", Transaction ID: " + transactionId + ", Date: " + orderDate;
// }
//}
//
////Review Class
//class Review {
// private String reviewText;
// private int rating;
//
// public Review(String reviewText, int rating) {
//     this.reviewText = reviewText;
//     this.rating = rating;
// }
//
// public String getReviewText() {
//     return reviewText;
// }
//
// public int getRating() {
//     return rating;
// }
//
// @Override
// public String toString() {
//     return "Rating: " + rating + "/5, Review: " + reviewText;
// }
//}
//
////Restaurant Class
//class Restaurant {
// private String name;
// private String location;
// private String contact;
// private List<MenuItem> menu;
// private List<Order> orders;
// private List<Review> reviews;
//
// public Restaurant(String name, String location, String contact) {
//     this.name = name;
//     this.location = location;
//     this.contact = contact;
//     this.menu = new ArrayList<>();
//     this.orders = new ArrayList<>();
//     this.reviews = new ArrayList<>();
// }
//
// public void addMenuItem(MenuItem item) {
//     menu.add(item);
// }
//
// public void editMenuItem(MenuItem item, String name, double price, String description, String category, int stock) {
//     item.setStock(stock);
// }
//
// public void removeMenuItem(MenuItem item) {
//     menu.remove(item);
// }
//
// public List<MenuItem> getMenu() {
//     return menu;
// }
//
// public void addOrder(Order order) {
//     orders.add(order);
// }
//
// public void trackOrderPaymentStatus(Order order) {
//     System.out.println("Order ID: " + order.getTransactionId() + " - Payment Status: " + (order.isPaid() ? "Paid" : "Not Paid"));
// }
//
// public void updateStock(MenuItem item, int stock) {
//     item.setStock(stock);
// }
//
// public String getName() {
//     return name;
// }
//
// public String getLocation() {
//     return location;
// }
//
// public String getContact() {
//     return contact;
// }
//}
//
////DeliveryPartner Class
//class DeliveryPartner {
// private String name;
// private String contact;
// private List<Order> assignedOrders;
// private Map<Order, String> deliveryStatus;
// private boolean isActive; // Status for deactivation
//
// public DeliveryPartner(String name, String contact) {
//     this.name = name;
//     this.contact = contact;
//     this.assignedOrders = new ArrayList<>();
//     this.deliveryStatus = new HashMap<>();
//     this.isActive = true; // Active by default
// }
//
// public void deactivateAccount() {
//     this.isActive = false;
// }
//
// public void reactivateAccount() {
//     this.isActive = true;
// }
//
// public boolean isActive() {
//     return isActive;
// }
//
// public void acceptOrder(Order order) {
//     assignedOrders.add(order);
//     deliveryStatus.put(order, "Accepted");
// }
//
// public void markDeliveryStatus(Order order, String status) {
//     deliveryStatus.put(order, status);
// }
//
// public void rateRestaurant(Restaurant restaurant, int rating) {
//     System.out.println("Delivery Partner rated restaurant " + restaurant.getName() + ": " + rating + "/5");
// }
//
// public void rateCustomer(Customer customer, int rating) {
//     System.out.println("Delivery Partner rated customer " + customer.getName() + ": " + rating + "/5");
// }
//
// public void viewAssignedOrders() {
//     for (Order order : assignedOrders) {
//         System.out.println("Assigned Order: " + order);
//     }
// }
//
// public String getName() {
//     return name;
// }
//
// public String getContact() {
//     return contact;
// }
//}
//
////RestaurantOwner Class
//class RestaurantOwner {
// private String name;
// private String contact;
// private Restaurant restaurant;
//
// public RestaurantOwner(String name, String contact, Restaurant restaurant) {
//     this.name = name;
//     this.contact = contact;
//     this.restaurant = restaurant;
// }
//
// public void deactivateAccount() {
//     // Logic to deactivate the account
//     System.out.println("Restaurant owner " + name + " account deactivated.");
// }
//
// public void reactivateAccount() {
//     // Logic to reactivate the account
//     System.out.println("Restaurant owner " + name + " account reactivated.");
// }
//
// public String getName() {
//     return name;
// }
//
// public Restaurant getRestaurant() {
//     return restaurant;
// }
//}
//
////Admin Class
//class Admin {
// private Map<String, DeliveryPartner> deliveryPartners;
// private Map<String, RestaurantOwner> restaurantOwners;
// private Map<String, Customer> customers;
//
// public Admin() {
//     deliveryPartners = new HashMap<>();
//     restaurantOwners = new HashMap<>();
//     customers = new HashMap<>();
// }
//
// public void registerDeliveryPartner(String name, DeliveryPartner partner) {
//     deliveryPartners.put(name, partner);
// }
//
// public void registerRestaurantOwner(String name, RestaurantOwner owner) {
//     restaurantOwners.put(name, owner);
// }
//
// public void registerCustomer(String name, Customer customer) {
//     customers.put(name, customer);
// }
//
// // Deactivate customer with confirmation
// public void deactivateCustomer(String name, Scanner scanner) {
//     Customer customer = customers.get(name);
//     if (customer != null) {
//         System.out.println("Are you sure you want to deactivate customer " + name + "? (yes/no)");
//         String response = scanner.nextLine();
//         if (response.equalsIgnoreCase("yes")) {
//             customer.deactivateAccount();
//             System.out.println("Customer " + name + " deactivated.");
//         } else {
//             System.out.println("Customer " + name + " not deactivated.");
//         }
//     } else {
//         System.out.println("Customer not found.");
//     }
// }
//
// // Deactivate restaurant owner with confirmation
// public void deactivateRestaurantOwner(String name, Scanner scanner) {
//     RestaurantOwner owner = restaurantOwners.get(name);
//     if (owner != null) {
//         System.out.println("Are you sure you want to deactivate restaurant owner " + name + "? (yes/no)");
//         String response = scanner.nextLine();
//         if (response.equalsIgnoreCase("yes")) {
//             owner.deactivateAccount();
//             System.out.println("Restaurant owner " + name + " deactivated.");
//         } else {
//             System.out.println("Restaurant owner " + name + " not deactivated.");
//         }
//     } else {
//         System.out.println("Restaurant owner not found.");
//     }
// }
//
// // Deactivate delivery partner with confirmation
// public void deactivateDeliveryPartner(String name, Scanner scanner) {
//     DeliveryPartner partner = deliveryPartners.get(name);
//     if (partner != null) {
//         System.out.println("Are you sure you want to deactivate delivery partner " + name + "? (yes/no)");
//         String response = scanner.nextLine();
//         if (response.equalsIgnoreCase("yes")) {
//             partner.deactivateAccount();
//             System.out.println("Delivery partner " + name + " deactivated.");
//         } else {
//             System.out.println("Delivery partner " + name + " not deactivated.");
//         }
//     } else {
//         System.out.println("Delivery partner not found.");
//     }
// }
//}
//
////Main Class
//public class FoodDelivery {
// public static void main(String[] args) {
//     Scanner scanner = new Scanner(System.in);
//
//     // Create Admin
//     Admin admin = new Admin();
//
//     // Create Restaurant Owner and register
//     Restaurant pizzaPlace = new Restaurant("Pizza Place", "New York", "123-456-7890");
//     RestaurantOwner pizzaOwner = new RestaurantOwner("Alice", "987-654-3210", pizzaPlace);
//     admin.registerRestaurantOwner(pizzaOwner.getRestaurant().getName(), pizzaOwner);
//
//     // Register Delivery Partner
//     System.out.println("Register delivery partner:");
//     System.out.println("Enter partner name:");
//     String partnerName = scanner.nextLine();
//     System.out.println("Enter partner contact:");
//     String partnerContact = scanner.nextLine();
//     DeliveryPartner deliveryPartner = new DeliveryPartner(partnerName, partnerContact);
//     admin.registerDeliveryPartner(partnerName, deliveryPartner);
//
//     // Register Customer
//     System.out.println("Register customer:");
//     System.out.println("Enter customer name:");
//     String customerName = scanner.nextLine();
//     System.out.println("Enter customer email:");
//     String customerEmail = scanner.nextLine();
//     System.out.println("Enter customer phone number:");
//     String customerPhone = scanner.nextLine();
//     System.out.println("Enter customer address:");
//     String customerAddress = scanner.nextLine();
//     Customer customer = new Customer(customerName, customerEmail, customerPhone, customerAddress);
//     admin.registerCustomer(customerName, customer);
//
//     // Admin deactivates customer with confirmation
//     System.out.println("Admin: Deactivating customer...");
//     admin.deactivateCustomer(customerName, scanner);
//
//     // Admin deactivates restaurant owner with confirmation
//     System.out.println("Admin: Deactivating restaurant owner...");
//     admin.deactivateRestaurantOwner(pizzaOwner.getRestaurant().getName(), scanner);
//
//     // Admin deactivates delivery partner with confirmation
//     System.out.println("Admin: Deactivating delivery partner...");
//     admin.deactivateDeliveryPartner(deliveryPartner.getName(), scanner);
// }
//}*/
