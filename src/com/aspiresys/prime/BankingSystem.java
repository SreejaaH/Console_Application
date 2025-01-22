package com.aspiresys.prime;
public class BankingSystem {    
    public static void main(String args[]) {    
        Bank branch1;  
        branch1 = new KVB(); 
        System.out.println("Rate of Interest for KVB is: " + branch1.getRateOfInterest() + " % in 2025");    

        branch1 = new SIB();  
        System.out.println("Rate of Interest for SIB is: " + branch1.getRateOfInterest() + " % in 2025");    
    }
}
	


