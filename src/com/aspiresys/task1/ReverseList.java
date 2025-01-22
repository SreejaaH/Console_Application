package com.aspiresys.task1;
	import java.util.*;

	public class ReverseList {
	    public static void main(String[] args) {
	        List<Integer> list = new ArrayList<>(Arrays.asList(3, 5, 8, 1, 0));
	        
	        System.out.println("Original List: " + list);

	        
	        Collections.reverse(list);
	        System.out.println("Reversed List: " + list);
	        
	        
	        Collections.sort(list,Collections.reverseOrder());
	        System.out.println("Sorted List: " + list);
	        
	        
	    }
	}


