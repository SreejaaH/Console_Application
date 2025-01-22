package com.aspiresys.task4;
import java.util.PriorityQueue;
import java.util.Comparator;

	class Employee {
	    int id;
	    String name;
	    int performanceRating;  

	    public Employee(int id, String name, int performanceRating) {
	        this.id = id;
	        this.name = name;
	        this.performanceRating = performanceRating;
	    }

	    @Override
	    public String toString() {
	        return "Employee{id=" + id + ", name='" + name + "', rating=" + performanceRating + "}";
	    }
	}
	public class OtherFunctionsPriorityQueue {
	    public static void main(String[] args) {
	        Comparator<Employee> employeeComparator = (employee1, employee2) -> Integer.compare(employee1.performanceRating, employee2.performanceRating);
	        PriorityQueue<Employee> employeeQueue = new PriorityQueue<>(employeeComparator);
	        employeeQueue.offer(new Employee(1, "Sandhiya", 15));
	        employeeQueue.offer(new Employee(2, "Archana", 16));
	        employeeQueue.offer(new Employee(3, "Balu", 14));
	        employeeQueue.offer(new Employee(4, "Mangai", 12));
	        Employee manju = new Employee(5, "Manju", 17);
	        System.out.println("Does the queue contain Manju? " + employeeQueue.contains(manju));
	        System.out.println("Print the Highest Priority Employee): " + employeeQueue.peek());
	        employeeQueue.offer(new Employee(6, "Raj", 16));
	        System.out.println("\nEmployees in priority order:");
	        while (!employeeQueue.isEmpty()) {
	            System.out.println(employeeQueue.poll());  
	        }
	    }
	}


