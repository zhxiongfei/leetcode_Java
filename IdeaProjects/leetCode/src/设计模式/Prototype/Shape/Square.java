package 设计模式.Prototype.Shape;

public class Square extends Shape {
    public Square() {
        type = "Square";
    }

    @Override
    void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
