package com.aspiresys.prime;
import java.util.Scanner; 
public class DecimalNumberReversal {

	public static void main(String[] args) { 

        // Scanner is created 

        Scanner scanner = new Scanner(System.in); 

         

        System.out.print("Enter a 5-digit number: "); 

        int number = scanner.nextInt(); 

         

        //condition 

        if (number < 10000 || number > 99999) { 

            System.out.println("Please enter a valid 5-digit number."); 

        } 
        
        else { 

                        int reversedNumber = 0; 

             

         while (number != 0) { 

                int lastDigit = number % 10;   

                reversedNumber = reversedNumber * 10 + lastDigit;   

                number /= 10;  // Remove the last digit 

            } 

             

            // Display the reversed number 

            System.out.println("Reversed number: " + reversedNumber); 

        } 

                 // Close the scanner 

        scanner.close(); 

    } 

} 

