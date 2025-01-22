package com.aspiresys.task3;
import java.util.*;
public class DequeueExample {
	
		    public static void main(String[] args) {
	        Deque<Object> deque = new ArrayDeque<>();
	        deque.addFirst("Hello");         
	        deque.addFirst(3.14);  
	        deque.add(500);
	        deque.addLast(100); 
	        deque.addLast(true);       
	        System.out.println("Deque after adding elements: " + deque);
	        System.out.println("Removed from front: " + deque.removeFirst());  
	        System.out.println("Removed from rear: " + deque.removeLast());    
	        System.out.println("Deque after removing elements: " + deque);
	        System.out.println("First element: " + deque.peekFirst());  
	        System.out.println("Last element: " + deque.peekLast());    
	    }
	}



