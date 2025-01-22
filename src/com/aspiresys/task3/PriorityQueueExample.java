package com.aspiresys.task3;
import java.util.PriorityQueue;
import java.util.Comparator;
	public class PriorityQueueExample {
	    public static void main(String[] args) {
	        int[] array = {30, 10, 50, 20, 40};
	        PriorityQueue<Integer> PriorityValue = new PriorityQueue<>();
	        for (int num : array) {
	        	PriorityValue.add(num); 
	        }
	        System.out.println("Priority Flow of Numbers:");
	        while (!PriorityValue.isEmpty()) {
	            System.out.println(PriorityValue.poll()); //removes top element and prints the remaining
	        }
	    }
	}

		





