package com.aspiresys.task2;
import java.util.ArrayList;
import java.util.LinkedList;

class Student {
    private int RollNum;  
    private String name;
    private String department;  

    
    public Student(int RollNum, String name, String department) { 
        this.RollNum = RollNum;
        this.name = name;
        this.department = department;
    }

    public int getRollNum() {
        return RollNum;
    }

    public void setRollNum(int RollNum) {
        this.RollNum = RollNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student [RollNum=" + RollNum + ", Name=" + name + ", Department=" + department + "]";
    }
}

public class ArrayAndLinkedListExample {
    public static void main(String[] args) {
        ArrayList<Student> studentListArrayList = new ArrayList<>();
        studentListArrayList.add(new Student(1, "Angelina", "Computer Science"));
        studentListArrayList.add(new Student(2, "Dinesh", "Mechanical"));
        studentListArrayList.add(new Student(3, "Gowtham", "Electronics"));
        studentListArrayList.add(new Student (4,"Merlin","Computer science"));

        System.out.println("Students in ArrayList:");
        for (Student student : studentListArrayList) {
            System.out.println(student);
        }

        studentListArrayList.remove(1);
        studentListArrayList.get(0).setName("Sheela");//Update
        System.out.println("\nAfter removal and update in ArrayList:");
        for (Student student : studentListArrayList) {
            System.out.println(student);
        }
       
        LinkedList<Student> studentListLinkedList = new LinkedList<>();
        studentListLinkedList.add(new Student(1, "Teena", "Computer Science"));
        studentListLinkedList.add(new Student(2, "Manju", "Mechanical"));
        studentListLinkedList.add(new Student(3, "Sarath", "Electronics"));

        System.out.println("\nStudents in LinkedList:");
        for (Student student : studentListLinkedList) {
            System.out.println(student);
        }

        studentListLinkedList.remove(1);
        studentListLinkedList.get(0).setDepartment("Aeronautical");

        System.out.println("\nAfter removal and update in LinkedList:");
        for (Student student : studentListLinkedList) {
            System.out.println(student);
        }
    }
}
