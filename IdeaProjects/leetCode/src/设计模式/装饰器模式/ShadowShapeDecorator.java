package 设计模式.装饰器模式;

public class ShadowShapeDecorator extends ShapeDecorator {
    public ShadowShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();

        setShadow(decoratedShape);
    }

    private void setShadow(Shape decoratedShape){
        System.out.println("Shape : Shadow");
    }
}
