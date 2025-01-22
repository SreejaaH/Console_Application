package com.aspiresys.task2;
	import java.util.LinkedList;
	import java.util.Queue;

	public class QueueExample {
	    public static void main(String[] args) {
	        Queue<Integer> queue = new LinkedList<>();

	        // Adding
	        queue.add(100);
	        queue.add(200);
	        queue.add(300);
	        queue.add(400);

	       	System.out.println("Queue after adding elements: " + queue);

	        // Peek
	        System.out.println("Peek first element: " + queue.peek());

	        // Remove
	        queue.remove();
	        System.out.println("Queue after removing an element: " + queue);

	        // Check the size of the queue
	        System.out.println("Queue size: " + queue.size());

	        // Check if the queue is empty
	        System.out.println("Is the queue empty? " + queue.isEmpty());

	        // Add more elements to the queue
	        queue.offer(500);
	        queue.offer(600);
	        queue.offer(700);
	        System.out.println("Queue after adding more elements: " + queue);//gives false when cannot accomodate.

	        // Peek
	        System.out.println("Peek after adding more elements: " + queue.peek());
	    }
	}



