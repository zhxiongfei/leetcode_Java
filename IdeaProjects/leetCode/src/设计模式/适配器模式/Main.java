package 设计模式.适配器模式;

public class Main {

    public static void main(String[] args) {

        Player b = new Forwards("巴蒂尔");
        b.attack();

        Player m = new Guards("麦格尔雷迪");
        m.attack();

        Player ym = new Translator("姚明");
        ym.attack();
        ym.defense();
    }
}
