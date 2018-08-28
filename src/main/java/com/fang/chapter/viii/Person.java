package com.fang.chapter.viii;

public class Person {
    public Person getPerson(){
        return getOne();
    }
    public Person  getOne(){
        System.out.println("Person,getOne()");
        return new Person();
    }

    public static void main(String[] args) {
        Person p = new Student();
        p.getPerson();
    }
}

class Student extends Person{

    public Person  getOne(){
        System.out.println("Student.getOne()");
        return new Student();
    }
}
