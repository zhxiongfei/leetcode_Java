package com.fx;

public class Person implements Comparable<Person>{

    public int age;

    public Person(int age) {
        this.age = age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(Person o) {
        return age - o.age;
    }

    @Override
    public String toString() {
        return age + "_" ;
    }
}
