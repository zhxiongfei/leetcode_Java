package 设计模式.适配器模式;

public class Translator extends Player {

    private ForeignCenter foreignCenter;

    public Translator(String name) {
        super(name);
        foreignCenter = new ForeignCenter(name);
    }

    @Override
    public void attack() {
        foreignCenter.进攻();
    }

    @Override
    public void defense() {
        foreignCenter.防守();
    }
}
