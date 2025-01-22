package com.aspiresys.task;


	class Student {
	    static int mark = 490;
	    int markPercentage = 98;

	    	static class ScoreValue {
	        void display() {
	            System.out.println("My mark is: " + mark + " out of 500");
	            //System.out.println("My percentage is " + markPercentage); 
	        }
	    }

	  	    class Eligibility {
	        void display() {
	            System.out.println("With my marks " + mark + "and percentage" + markPercentage + "I am eligible for admission.");
	        }
	    }
	}

	public class StaticNestedClassExample {
	    public static void main(String[] args) {
	       
	        Student.ScoreValue scoreStatus = new Student.ScoreValue();
	        scoreStatus.display();

	        Student student = new Student();
	        Student.Eligibility eligibilityCriteria = student.new Eligibility();
	        eligibilityCriteria.display();
	    }
	}



