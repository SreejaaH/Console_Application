package com.aspiresys.prime; 

import java.util.Scanner;  

public class EmployeeEligibility { 

	public static void main(String[] args) {  

 

// Scanner is created  

 

		Scanner scanner = new Scanner(System.in);  

 

// Gathering employee details for eligibility	  

 

		System.out.println("Does the employee have a passport? (true/false):");  

 

		boolean havingPassport = scanner.nextBoolean();  

 

		scanner.nextLine(); // To go to the next line  

 

		System.out.println("Enter 'good' or 'bad' for employee's communication skills:");  

 

		String communication = scanner.nextLine().trim(); //To avoid white space 

 

	System.out.println("Enter employee's training feedback (good/bad)");  

 

	String trainingFeedback = scanner.nextLine().trim();  

 

	System.out.println("Enter the number of years of employee's experience:");  

 

	 int experience = scanner.nextInt();  

 

	 System.out.println("Enter employee's age:");  

 

	 int age = scanner.nextInt();  

 

// Check eligibility using if-else statements  

 

if (havingPassport) {  

 

if (communication.equalsIgnoreCase("good")) {  

if (trainingFeedback.equalsIgnoreCase("good")) { //To ignore case (upper or lower) 

 

if (experience >= 2) {  

 

if (age >= 23) {  

 

System.out.println("The employee is eligible for on-site.");  

 

}  

 

}  

 

}  

 

}  

else {  

 

System.out.println("The employee is not eligible for on-site.");  

 

}  

 

}  

scanner.close();  

 

}  

 

} 