package com.aspiresys.task3;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetExample {
    public static class Teacher implements Comparable<Teacher> {
        String name;
        String subject;

        public Teacher(String name, String subject) {
            this.name = name;
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        @Override
        public String toString() {
            return "Teacher [name=" + name + ", subject=" + subject + "]";
        }

        
        @Override
        public int compareTo(Teacher other) {
            
            int nameComparison = this.name.compareTo(other.name);
            if (nameComparison != 0) {
                return nameComparison;
            }
            return this.subject.compareTo(other.subject);
        }
    }

    public static class School {
        public static void main(String[] args) {
            TreeSet<Teacher> teachers = new TreeSet<>();

            Teacher socialTeacher = new Teacher("Jacks", "Social");

            teachers.add(socialTeacher);
            teachers.add(new Teacher("Sreejaa", "Maths"));
            teachers.add(new Teacher("Ganesh", "Science"));
            teachers.add(new Teacher("Sumesh", "English"));
            teachers.add(new Teacher("Yash", "Physics"));
            teachers.add(new Teacher("Roshan", "Biology"));
            teachers.add(socialTeacher); 

            
            displayTeachers(teachers);
        }
        public static void displayTeachers(TreeSet<Teacher> teachers) {
            Iterator<Teacher> iterator = teachers.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
