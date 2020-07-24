package 设计模式.适配器模式;

public abstract class Player {
    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract void attack();  // 进攻
    public abstract void defense(); // 防守
}
