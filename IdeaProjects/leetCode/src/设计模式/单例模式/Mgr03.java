package 设计模式.单例模式;

/**
 *
 * Lazy Loading
 * 也称为懒汉式
 *
 * 虽然达到了按需初始化的目的, 但却带来了线程不安全的问题
 *
 * */
public class Mgr03 {

    private static Mgr03 INSTANCE;

    private Mgr03(){}

    public static Mgr03 getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

}
