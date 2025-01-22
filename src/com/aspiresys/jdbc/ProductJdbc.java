package com.aspiresys.jdbc;

import java.sql.*;
import java.util.Scanner;

public class ProductJdbc {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        //addPlayer();
        //deletePlayer();
        updatePlayer();  
    }

    public static void addPlayer() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/world";
        final String username = "root";
        final String password = "Aspire@123";
        
        
        System.out.println("Enter name:");
        String name = scanner.next();
        
        System.out.println("Enter roll number:");
        int roll = scanner.nextInt();
        
        System.out.println("Enter class:");
        int cls = scanner.nextInt();

        String sql = "INSERT INTO player (name, roll_no, class) VALUES (?, ?, ?)"; 
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);  
            statement.setInt(2, roll);     
            statement.setInt(3, cls);     
      
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted == 1) {
                System.out.println("Inserted successfully");
            } else {
                System.out.println("Insertion failed");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void deletePlayer() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/world";
        final String username = "root";
        final String password = "Aspire@123";
  
        System.out.println("Enter the player name to delete: ");
        String playerName = scanner.nextLine();

        String deleteSql = "DELETE FROM player WHERE name = ?";  
      
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(deleteSql);
            statement.setString(1, playerName);  

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 1) {
                System.out.println("Deleted successfully");
            } else {
                System.out.println("Deletion failed");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public static void updatePlayer() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/world";
        final String username = "root";
        final String password = "Aspire@123";

        System.out.println("Enter the player name to update:");
        String name = scanner.nextLine();

        System.out.println("Enter new roll number:");
        int roll_number = scanner.nextInt();

        System.out.println("Enter new class:");
        int class_number = scanner.nextInt();

        String updateSql = "UPDATE player SET roll_no = ?, class = ? WHERE name = ?";
        
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement(updateSql);
            statement.setInt(1, roll_number); 
            statement.setInt(2, class_number);  
            statement.setString(3, name);      

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 1) {
                System.out.println("Updated successfully");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }
}
