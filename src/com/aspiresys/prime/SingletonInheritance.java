package com.aspiresys.prime;
public class SingletonInheritance {
    public static void main(String[] args) {
        
        Student student = new Student();

        
        student.name = "Mary";
        student.display();      
        student.name();
    }
}
