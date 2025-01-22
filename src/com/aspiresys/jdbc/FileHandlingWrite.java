package com.aspiresys.jdbc;
import java.io.FileWriter;

public class FileHandlingWrite {
	public static void main(String[] args) {
		try {
			FileWriter fileWrite = new FileWriter("output.txt",true);
			//FileWriter fileWrite2 = new FileWriter("output.txt",true);
			
			fileWrite.append(" Yes that's right");
			
			fileWrite.close();
			System.out.println("Successfully printed");
		}
		catch(Exception error)
		{
			System.out.println("Not Executed");
		}
	}
	
}
