package com.aspiresys.task4;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegularExpressionEmail {

    private static final String CorrectEmail = 
    		"^[a-zA-Z0-9_+&*-]+(?:[a-zA-Z0-9_+&*-]*[a-zA-Z0-9])?@([a-zA-Z]+\\.)+(?:com|in|org)$";


    public static void main(String[] args) {
        
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Enter the email address");

        String userEmail = inputScanner.nextLine();

        
        Pattern emailPattern = Pattern.compile(CorrectEmail);

        
        Matcher emailMatcher = emailPattern.matcher(userEmail);

        
        if (emailMatcher.matches()) {
            System.out.println("Valid email address.");
        } else {
            System.out.println("Invalid email address.");
        }

      
        inputScanner.close();
    }
}
