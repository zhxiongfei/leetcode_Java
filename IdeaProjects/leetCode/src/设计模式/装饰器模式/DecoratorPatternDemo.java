package 设计模式.装饰器模式;

public class DecoratorPatternDemo {

    public static void main(String[] args) {

        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(circle);

        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle with red border");
        redCircle.draw();

        System.out.println("\nRectangle with red border");
        redRectangle.draw();

        System.out.println("\nCircle With shadow");
        ShadowShapeDecorator shadowCircle = new ShadowShapeDecorator(circle);
        shadowCircle.draw();
    }

}
