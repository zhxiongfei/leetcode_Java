package 设计模式.单例模式;

/**
 *
 * Lazy Loading
 * 也称为懒汉式
 *
 * 虽然达到了按需初始化的目的, 但却带来了线程不安全的问题
 * 可以通过 synchronized 解决, 但也带来效率下降
 *
 * */

public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04(){}

    public static synchronized Mgr04 getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }
    
}
