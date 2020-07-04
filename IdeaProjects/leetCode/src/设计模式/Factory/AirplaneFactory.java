package 设计模式.Factory;

public class AirplaneFactory{
    public static Moveable create(){
        // 这里可以处理业务逻辑所需要的定制过程
        return new Airplane();
    }
}
