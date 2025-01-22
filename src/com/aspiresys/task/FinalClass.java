package com.aspiresys.task;


	final class SeniorManager {
	    private String name;
	    private int experience;

	    public SeniorManager(String name, int experience) {
	        this.name = name;
	        this.experience = experience;
	    }

	    public void displayInfo() {
	        System.out.println("Name: " + name + ", Experience: " + experience);
	    }
	}

	
	// class Employee extends SeniorManager { 
//	     private String name1;
//	     private int experience1;
	//
//      public void Credentials1(String name1, int experience1) {
//      this.name1 = name1;
//       this.experience1 = experience1;
	
//	     public void displayInfo() {
//	         super.displayInfo();
//	         System.out.println("Name: " + name1 + ", Experience: " + experience1);
//	     }
	// } //Tried to extend it throws error.

	public class FinalClass {
	    public static void main(String[] args) {
	    	SeniorManager seniormanager = new SeniorManager("Sanjay", 11);
	        seniormanager.displayInfo();
	    }
	}



