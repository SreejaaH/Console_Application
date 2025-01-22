package com.aspiresys.task4;
public class OneTryMultipleCatchBlock {
		    public static void main(String[] args) {
	        int[] numbers = {1, 2, 3,4,5}; 
	        try {
	            int result = 10 / 0;  
	            int index = numbers[10];  
	            throw new ArithmeticException("Cannot divide by zero");
	            
	        } 
	        
	        catch (ArithmeticException value) {
	            System.out.println("Print the exception message: " + value.getMessage());
	     
	        } 
	        
	        catch (ArrayIndexOutOfBoundsException value1) {
	            System.out.println("Print the exception message " + value1.getMessage());
	        }
	        
	               
	        finally {
	        	System.out.println("The provided exceptions are right");
	        	
	        }
		    }
}

