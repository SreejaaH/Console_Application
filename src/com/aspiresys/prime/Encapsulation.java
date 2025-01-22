package com.aspiresys.prime;

public class Encapsulation {

    public static void main(String[] args) {
        
        Person1 person1 = new Person1();

        
        person1.setName("John");
        person1.setAge(30);

        
        System.out.println("Name: " + person1.getName());
        System.out.println("Age: " + person1.getAge());
    }
}


