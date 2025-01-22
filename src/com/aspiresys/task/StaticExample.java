package com.aspiresys.task;
	class Number {
	    static int count = 0; 
	    public static void incrementCount() { 
	        count++;
	    }
	}
	public class StaticExample {
	    public static void main(String[] args) {
	        Number.incrementCount();  //method belongs to the same class itself (need not to be called separately with object)
	        
	        System.out.println(Number.count);  
	    }
	}



