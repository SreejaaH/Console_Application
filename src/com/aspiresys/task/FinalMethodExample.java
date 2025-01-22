package com.aspiresys.task;
	class Manager {
	    public final void displayMessage() {
	        System.out.println("I am working here since 5 years");
	    }
	}

	class Developer extends Manager {
	    /*
	    public void displayMessage() {  
	    	        System.out.println("I am working here since 7 years.");
	    }
	   */
	}

	public class FinalMethodExample {
	    public static void main(String[] args) {
	    	Manager manager = new Manager();
	        manager.displayMessage();
	        Developer developer = new Developer();
	        developer.displayMessage();  
	        
	          
	    }
	}


