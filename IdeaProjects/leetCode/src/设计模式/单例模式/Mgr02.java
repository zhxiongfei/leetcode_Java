package 设计模式.单例模式;

/**
 *
 * 跟 01 一个意思
 *
 * */
public class Mgr02 {

    private static final Mgr02 INSTANCE;
    static {
        INSTANCE = new Mgr02();
    }

    public static Mgr02 getInstance(){return INSTANCE;}
}
