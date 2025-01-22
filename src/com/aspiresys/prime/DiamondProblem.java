package com.aspiresys.prime;

/*	class Team1 { 
	void funEvent() { 
		System.out.println("Eligible for startrek"); } 
} 

class Team2 { 
	void funEvent() {
		System.out.println("Not eligible for startrek"); } 
} 

class DiamondProblem extends Team1, Team2 { 
	
	public static void main(String[] args) 
	{ 
		DiamondProblem diamondProblem = new DiamondProblem(); 
		diamondProblem.funEvent(); 
	} 
}*/



interface Team1 { 
 void funEvent(); 
} 


interface Team2 { 
 void funEvent(); 
} 


class Members implements Team1, Team2 { 


 public void funEvent() { 
     System.out.println("startrek is eligible for team1");
     System.out.println("startrek is not eligible for team2");
 }
} 


class DiamondProblem { 
 
 public static void main(String[] args) { 
     Members members = new Members(); 
     members.funEvent(); 
 } 
}






