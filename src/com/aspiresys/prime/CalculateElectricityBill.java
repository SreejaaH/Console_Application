package com.aspiresys.prime;
import java.util.Scanner; 
public class CalculateElectricityBill {
	
public static void main(String[] args) {
	
Scanner scanner = new Scanner(System.in); 
System.out.print("Enter the number of units consumed: "); 

int units = scanner.nextInt(); 

double billAmount = 0.0; 
 

if (units > 1000) { 

billAmount = billAmount + (units - 1000) * 10; 

units = 1000; 

} 

if (units > 500) { 

billAmount = billAmount + (units - 500) * 5; 

units = 500; 

} 
if (units > 200) { 

billAmount = billAmount + (units - 200) * 2; 

units = 200; 

} 
billAmount = billAmount + units * 1; 

System.out.println("The total electricity bill is: Rs." + billAmount); 
scanner.close(); 

} 

}

