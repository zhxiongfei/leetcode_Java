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

public class Mgr05 {
    private static Mgr05 INSTANCE;

    private Mgr05(){}

    public static Mgr05 getInstance(){
        if (INSTANCE == null){
            // 妄图通过减小同步代码块的方式提高效率, 然后不可行
            synchronized (Mgr05.class){
                INSTANCE = new Mgr05();
            }
        }
        return INSTANCE;
    }
}
