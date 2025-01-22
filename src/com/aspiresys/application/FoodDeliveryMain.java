package com.aspiresys.application;
import java.sql.Connection;
import java.util.Scanner;

public class FoodDeliveryMain {
    public static void main(String[] args) {
        try {
            // Establishing database connection
            Connection food = DBConnection.getConnection();
            Scanner scanner = new Scanner(System.in);
            MenuController menuController = new MenuController(food, scanner);
            menuController.startMenu(); // Start the application menu flow
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}




