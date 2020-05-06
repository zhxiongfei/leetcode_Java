package com.fx;

public class Main {
    public static void main(String[] args){

        ArrayList list = new ArrayList<>();

        Person p1 = new Person(10,"A");
        Person p2 = new Person(11,"B");
        Person p3 = new Person(12,"C");
        Person p4 = new Person(13,"D");
        Person p5 = new Person(14,"E");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        System.out.print(list);
    }
}
