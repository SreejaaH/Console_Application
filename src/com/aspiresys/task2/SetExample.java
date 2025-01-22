package com.aspiresys.task2;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetExample {
	public static void main(String[] args)
	{
		Set<String> set=new HashSet<>();//HashSet
		Set<Integer> set1=new LinkedHashSet<>();//LinkedHashSet
		Set<Double>set2=new TreeSet<>();//TreeSet
		set.add("Ramesh");
		set.add("Suresh");
		set.add("Ganesh");
		set1.add(700);
		set1.add(600);
		set1.add(800);
		set2.add(0.05);
		set2.add(0.02);
		set2.add(0.09);
		System.out.println(set);
		System.out.println(set1);
		System.out.println(set2);
		
	}

}
