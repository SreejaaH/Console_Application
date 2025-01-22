package com.aspiresys.jdbc;
import java.io.File;

public class FileHandlingDelete {
	public static void main(String[] args) {
		File fileDelete = new File("output.txt");
		if(fileDelete.delete())
		{
			System.out.println("Deleted successfully");
		}
		else {
			System.out.println("Not Deleted");
		}
		
	}

}
