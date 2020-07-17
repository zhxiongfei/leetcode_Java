package 设计模式.Prototype.Person.v1;

public class Test {

    public static void main(String[] args) throws Exception{

        Person p1 = new Person(8, "Jack", new Location("北京", 110));
        Person p2 = (Person) p1.clone();

        p2.age = 10;
        p2.name = "Rose";
        p2.location.city = "石家庄";
        p2.location.no = 310;
        System.out.println(p1);
        System.out.println(p2);

        System.out.println(p1 == p2);
        System.out.println(p1.location == p2.location);
    }
}

class Person implements Cloneable {
    int age;
    String name;

    Location location;

    public Person(int age, String name, Location location) {
        this.age = age;
        this.name = name;
        this.location = location;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}

class Location {
    String city;
    int no;

    public Location(String city, int no) {
        this.city = city;
        this.no = no;
    }

    @Override
    public String toString() {
        return "Location{" +
                "city='" + city + '\'' +
                ", no=" + no +
                '}';
    }
}