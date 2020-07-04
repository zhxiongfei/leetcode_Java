package 设计模式.SimpleFactory;

public class VehicelFactory {
    public static Car createCar(){
        // 这里可以处理业务逻辑所需要的定制过程
        return new Car();
    }

    public static Airplane createAirplane(){
        // 这里可以处理业务逻辑所需要的定制过程
        return new Airplane();
    }
}
