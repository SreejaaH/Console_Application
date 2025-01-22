package com.aspiresys.task1;
	import java.util.Arrays;
	import java.util.List;
	import java.util.ArrayList;
public class BasicFunctionsArray {
	    public static void main(String[] args) {
	      
	        int[] arr = {10, 20, 30, 40, 50};
	        
	       	System.out.println("Element at index 2: " + arr[2]); // Output: 30
	        
	        arr[3] = 100;  
	        System.out.println("Updated array: " + Arrays.toString(arr));  // Output: [10, 20, 30, 100, 50]
	        
	        System.out.println("Length of the array: " + arr.length); // Output: 5
	        
	      
	        Arrays.sort(arr);  // Sort the array in ascending order
	        System.out.println("Sorted array: " + Arrays.toString(arr)); // Output: [10, 20, 30, 50, 100]
	        
	        int[] copiedArray = Arrays.copyOf(arr, arr.length); 
	        System.out.println("Copied array: " + Arrays.toString(copiedArray));  // Output: [10, 20, 30, 50, 100]
	        
	     
	        Arrays.fill(arr, 0);  // Fill the entire array with 0
	        System.out.println("Array after filling with 0: " + Arrays.toString(arr));  // Output: [0, 0, 0, 0, 0]
	        
	        
	        List<Integer> list = new ArrayList<>();//ArrayList is created
	        for (int i = 0; i < arr.length; i++) {
	            list.add(arr[i]);  
	        }
	        System.out.println("Converted List: " + list);  // Output: [0, 0, 0, 0, 0]
	        System.out.println(list.toArray().toString());
	       
//	        int[] arrFromList = new int [list.size()] ;
	        for(int i1 : list) {
	        	System.out.println(i1);
	        }
//	      
//	        System.out.println("Array from List: " + arrFromList.toString());  // Output: [0, 0, 0, 0, 0]
	    }
	}


