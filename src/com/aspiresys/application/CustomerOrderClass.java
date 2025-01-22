import java.sql.*;
import java.util.*;

public class CustomerOrderClass {

    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            MenuController menuController = new MenuController(conn, scanner);
            menuController.startMenu(); // Start the application menu flow
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

class DBConnection {
    public static Connection getConnection() throws SQLException {
        try {
            // JDBC connection to MySQL database
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/food_delivery_app", "root", "password"); // Replace with your DB details
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Database connection failed: " + e.getMessage());
        }
    }
}

class MenuController {
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
            scanner.nextLine();  // Consume newline

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

class Customer {
    private Connection conn;
    private Scanner scanner;

    public Customer(Connection conn, Scanner scanner) {
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
                    scanner.nextLine();  // Consume newline

                    switch (choice) {
                        case 1:
                            viewMenu(order);
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
                System.out.println("1. Add Menu Item");
                System.out.println("2. View Menu Items");
                System.out.println("3. Back");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        addMenuItem();
                        break;
                    case 2:
                        viewMenuItems();
                        break;
                    case 3:
                        return;  // Back to main menu
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
        scanner.nextLine();  // Consume newline

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
        }
        System.out.println("Total Price: $" + totalPrice);
    }
}
