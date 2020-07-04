package 设计模式.AbstractFactory;

public class Main {
    public static void main(String[] args) {

//        AbstractFactory abstractFactory = new MagicFactory();
        AbstractFactory abstractFactory = new MordernFactory();

        abstractFactory.createFood().eat();
        abstractFactory.createVehicle().go();
        abstractFactory.createWeapon().attack();
    }
}

