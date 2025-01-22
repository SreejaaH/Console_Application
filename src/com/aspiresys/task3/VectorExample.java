package com.aspiresys.task3;
	import java.util.*;

	class Employee {
	    private int id;
	    private String name;

	   	public Employee(int id, String name) {
	        this.id = id;
	        this.name = name;
	    }

	    
	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    
	    @Override
	    public String toString() {
	        return "Employee [ID=" + id + ", Name=" + name + "]";
	    }
	}

	class Company {
	    private String companyName;
	    private Vector<Employee> employees; 

	    
	    public Company(String companyName) {
	        this.companyName = companyName;
	        this.employees = new Vector<>(); // Initializing Vector and storing employee values
	    }

	    public void addEmployee(Employee employeeName) {
	        employees.add(employeeName);
	    }

	    
	    public void displayEmployees() {
	        System.out.println("Employees in " + companyName + ":");
	        for (Employee employee : employees) {
	            System.out.println(employee);
	        }
	    }
	}

	public class VectorExample {
	    public static void main(String[] args) {
	        
	        Employee emp1 = new Employee(101, "Sreejaa");
	        Employee emp2 = new Employee(102, "Santhosh");
	        Employee emp3 = new Employee(103, "Jeeva");
	        Employee emp4 = new Employee(104, "Dinesh");
	        Employee emp5 = new Employee(105, "Soumya");
	        Employee emp6 = new Employee(106, "Shyam");

	        
	        Company company = new Company("Aspire Systems");

	       
	        company.addEmployee(emp1);
	        company.addEmployee(emp2);
	        company.addEmployee(emp3);
	        company.addEmployee(emp4);
	        company.addEmployee(emp5);
	        company.addEmployee(emp6);
	       
	        company.displayEmployees();
	    }
	}

