package 设计模式.SimpleFactory;

public class Car implements Moveable{
    @Override
    public void go() {
        System.out.println("Car run ~");
    }
}
