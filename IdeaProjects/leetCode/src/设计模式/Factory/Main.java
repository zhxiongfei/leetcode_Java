package 设计模式.Factory;

public class Main {

    public static void main(String[] args) {
        Moveable c = CarFactory.create();
        c.go();
    }

}

