package 设计模式.单例模式;

/**
 *
 * 饿汉式
 * 类加载到内存后, 就实例化一个单例, JVM保证线程安全
 *
 * 简单实用, 推荐实用
 * 唯一缺点 : 不管用到与否, 类装载时就完成实例化
 *
 * */
public class Mgr01 {

    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01(){};

    public static Mgr01 getInstance() {return INSTANCE;}
}
