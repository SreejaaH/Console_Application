package com.aspiresys.prime;

interface ManagerrHead {
    void getName();
    
}

interface ManagerHead1 {
	void getNumberOfYearsExperience();
}

public class BankingSystem1 {
    public static void main(String[] args) {
                ManagerDetails details = new ManagerDetails();
        details.getName();  
        details.getNumberOfYearsExperience();  
    }
}
