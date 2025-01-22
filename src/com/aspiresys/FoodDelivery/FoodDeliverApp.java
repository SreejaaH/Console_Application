package com.aspiresys.FoodDelivery;
import java.util.Scanner;

class FoodItem {
    private String name;
    private double price;
    private int stockQuantity;

    public FoodItem(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void reduceStock(int quantity) {
        stockQuantity -= quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Stock: " + stockQuantity + ")";
    }
}

class FoodDeliverApp {
    public static void main(String[] args) {
       
        FoodItem pizza = new FoodItem("Pizza", 12.99, 10);
        FoodItem burger = new FoodItem("Burger", 5.49, 20);
        FoodItem pasta = new FoodItem("Pasta", 8.99, 15);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        boolean continueOrdering = true;
        double totalAmount = 0.0;

        while (continueOrdering) {
            System.out.println("\n--- Food Menu ---");
            System.out.println("1. " + pizza);
            System.out.println("2. " + burger);
            System.out.println("3. " + pasta);

            System.out.print("Enter the number of the food item to add to the order (0 to finish): ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                continueOrdering = false;
                break;
            }
            FoodItem selectedFood = null;
            if (choice == 1) selectedFood = pizza;
            else if (choice == 2) selectedFood = burger;
            else if (choice == 3) selectedFood = pasta;

            if (selectedFood == null) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();

            if (quantity <= selectedFood.getStockQuantity()) {
                selectedFood.reduceStock(quantity);
                totalAmount = totalAmount + selectedFood.getPrice() * quantity;
                System.out.println(quantity + " " + selectedFood.getName() + "(s) added to your order.");
            } else {
                System.out.println("Not enough stock for " + selectedFood.getName() + ".");
            }
        }

        // Display order summary
        System.out.println("\n--- Order Summary ---");
        System.out.println("Customer: " + name);
        System.out.println("Address: " + address);
        System.out.println("Total Amount: $" + totalAmount);

        
        scanner.close();
    }
}
