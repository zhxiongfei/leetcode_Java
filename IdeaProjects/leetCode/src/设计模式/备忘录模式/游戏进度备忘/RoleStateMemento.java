package 设计模式.备忘录模式.游戏进度备忘;

public class RoleStateMemento {

    private int vit;    // 生命力
    private int atk;    // 攻击力
    private int def;    // 防御力

    public RoleStateMemento(int vit, int atk, int def) {
        this.vit = vit;
        this.atk = atk;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }
}
