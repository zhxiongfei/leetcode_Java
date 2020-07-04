package 设计模式.SimpleFactory;

public class Airplane implements Moveable{
    @Override
    public void go() {
        System.out.println("Airplane run ~");
    }
}
