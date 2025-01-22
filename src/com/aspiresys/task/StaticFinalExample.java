package com.aspiresys.task;
	class MathsConstants {
	    public static final double Pi = 3.14159;  
	    public static final int Max_BoilingPoint = 100;  
	}
	class Constants {
	    public static final double Pi = 3.15159;  
	    public static final int Max_BoilingPoint = 101;  
	}

	public class StaticFinalExample {
	    public static void main(String[] args) {
	        System.out.println("PI value: " + MathsConstants.Pi);
	        System.out.println("Max Limit: " + MathsConstants.Max_BoilingPoint);
	    }
	}



