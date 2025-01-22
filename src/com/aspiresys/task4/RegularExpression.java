package com.aspiresys.task4;
import java.util.Scanner;
import java.util.regex.*;
	 
	public class RegularExpression {
	    public static void main(String[] args)
	    {
	       
	        String regex = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
	        
	        Scanner scanner = new Scanner(System.in);
	        
	        System.out.println("Enter an IPv4 address:");
	        String ip = scanner.nextLine();
	        
	        Pattern pattern = Pattern.compile(regex);
	        
	        Matcher matcher = pattern.matcher(ip);
	        
	        if (matcher.matches()) {
	            System.out.println(ip + " is a valid IPv4 address.");
	        } else {
	            System.out.println(ip + " is not a valid IPv4 address.");
	        }
	       
	        scanner.close();
	    }
	}


