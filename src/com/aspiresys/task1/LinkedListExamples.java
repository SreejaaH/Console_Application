package com.aspiresys.task1;
import java.util.*;
import java.util.LinkedList;
public class LinkedListExamples {

    public static void main(String args[])
    {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(100); //add
        list1.add(200);
        list1.add(300);
        list1.add(400);
        list1.add(4,600);

        System.out.println("Initial LinkedList: " + list1);

       
        list1.set(1, 250); //set-replacing a value
        list1.remove(2); //remove
        list1.remove(0);
        System.out.println("Remove the last element" + list1.removeLast());
        
        System.out.println(list1);
        LinkedList<Integer> linkedList = new LinkedList<>();
		linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.addFirst(0); //at first
        linkedList.addLast(4); //at last
        
        for(int num : linkedList) {  
        	System.out.println(num);
        	//Read and print
        }
        
        
        System.out.println("Updated LinkedList: " + list1);
    }
}
