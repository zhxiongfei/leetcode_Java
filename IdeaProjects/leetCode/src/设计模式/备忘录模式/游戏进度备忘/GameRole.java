package 设计模式.备忘录模式.游戏进度备忘;

public class GameRole {

    private int vit;    // 生命力
    private int atk;    // 攻击力
    private int def;    // 防御力

    public void getInitState(){
        vit = 100;
        atk = 100;
        def = 100;
    }

    public void stateDisplay(){
        System.out.println("角色当前状态");
        System.out.println("生命力  " + vit);
        System.out.println("攻击力  " + atk);
        System.out.println("防御力  " + def);
    }

    public void fight(){
        this.vit = 0;
        this.atk = 0;
        this.def = 0;
    }

    public RoleStateMemento saveState(){
        return new RoleStateMemento(vit, atk, def);
    }

    public void RecoveryState(RoleStateMemento memento){
        this.vit = memento.getVit();
        this.atk = memento.getAtk();
        this.def = memento.getDef();
    }


}
