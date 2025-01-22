package com.aspiresys.prime;
class FatherInLaw {
    public void fatherInLaw_Name() {
        System.out.println("He is my father-in-law named Watson");
    }
}

class Father1 extends FatherInLaw {
    public void father_Name() {
        System.out.println("He is father named Hari.");
    }
}

class Son extends Father1 {
    public void son_Name() {
        System.out.println("I am son. My name is Anivash");
    }
}

class Daughter1 extends Father1 {
    public void daughter_Name() {
        System.out.println("I am daughter. My name is Dhivya");
    }
}

public class HybridInheritance {
    public static void main(String[] args) {
        Son displayStatement1 = new Son();
        displayStatement1.son_Name();
        displayStatement1.father_Name();

        Daughter1 displayStatement2 = new Daughter1();
        displayStatement2.daughter_Name();
        displayStatement2.father_Name();
    }
}
