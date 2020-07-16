package 设计模式.Prototype;

public class PrototypePatternDemo {

    public static void main(String[] args) {

        ShareCache.loadCache();

        Shape cloneShape1 = (Shape) ShareCache.getShape("1");
        System.out.println("Shape : " + cloneShape1.getType());

        Shape cloneShape2 = (Shape) ShareCache.getShape("2");
        System.out.println("Shape : " + cloneShape2.getType());

        Shape cloneShape3 = (Shape) ShareCache.getShape("3");
        System.out.println("Shape : " + cloneShape3.getType());
    }

}
