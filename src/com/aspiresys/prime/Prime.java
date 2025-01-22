package com.aspiresys.prime;
import java.util.Scanner;

public class Prime {
		public static boolean isPrime(int number) {
		if (number <= 1) { 

        	return false; 
		}
         

    	if (number == 2 || number == 3) { 

        	return true; 

    }     

 	if (number % 2 == 0 || number % 3 == 0) { 

             return false; 

    } 
			
        	for (int i = 5; i * i <= number; i = i+6) { 

        	if (number % i == 0 || number % (i + 2) == 0) {  //To skip even numbers 

             return false;   

        } 

    } 

    	return true;  

	}
		public static void main(String[] args) { 
			 

			Scanner scanner = new Scanner(System.in);  
			
			System.out.println("Enter the lower limit:");
			
			int lowerlimit = scanner.nextInt();  
			
			System.out.println("Enter the upper limit:");
			
			int upperlimit = scanner.nextInt();
			
			System.out.println("The prime number range between the upper limit" + upperlimit + "and the lower limit" + lowerlimit +":");
			for(int i = lowerlimit;i<=upperlimit;i++) {
				if (isPrime(i)) {;
					System.out.println(i);
				}
			}
			scanner.close();  
		}
}
