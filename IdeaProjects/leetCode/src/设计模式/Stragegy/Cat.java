package 设计模式.Stragegy;

public class Cat implements Comparable<Cat>{

    int height;
    int age;

    public Cat(int height, int age) {
        this.height = height;
        this.age = age;
    }

    @Override
    public int compareTo(Cat o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", age=" + age +
                '}';
    }
}
