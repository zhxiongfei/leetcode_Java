package 设计模式.SimpleFactory;

public class Main {

//        Car c = new Car();
//        c.go();

//        Airplane a = new Airplane();
//        a.go();

//    Moveable c = new Car();
//        c.go();
//
//    Moveable m = new Airplane();
//        m.go();
//
    public static void main(String[] args) {
        Moveable c = VehicelFactory.createCar();
        c.go();

        Moveable m = VehicelFactory.createAirplane();
        m.go();
    }

}

