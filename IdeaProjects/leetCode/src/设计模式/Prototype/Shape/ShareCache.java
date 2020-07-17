package 设计模式.Prototype.Shape;

import java.util.Hashtable;

public class ShareCache {
    private static Hashtable<String, Shape> shareMap = new Hashtable<String,Shape>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shareMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    // 对每种形状都运行数据库查询，并创建该形状
    // shapeMap.put(shapeKey, shape);
    // 例如，我们要添加三种形状
    public static void loadCache(){
        Circle circle = new Circle();
        circle.setId("1");
        shareMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shareMap.put(square.getId(), square);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shareMap.put(rectangle.getId(), rectangle);
    }
}
