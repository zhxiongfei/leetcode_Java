package 设计模式.单例模式;

/**
 *
 * 静态内部类方式
 * JVM保证单例
 * 加载内部类时，不会加载内部类, 这样可以实现懒加载
 *
 * */
public class Mgr07 {

    private Mgr07(){}

    public static class Mgr07Holder{
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance(){return Mgr07Holder.INSTANCE;}
}
