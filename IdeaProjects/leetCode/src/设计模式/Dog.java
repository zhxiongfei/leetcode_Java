package 设计模式;

import java.util.PriorityQueue;

public class Dog implements Comparable<Dog>{

    int weight;

    public Dog(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Dog o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "weight=" + weight +
                '}';

    }
}
