package 设计模式.备忘录模式.游戏进度备忘;

public class Main {

    public static void main(String[] args) {

        // 大战 boss 前
        GameRole Jack = new GameRole();
        // 游戏角色初始状态, 三个指标都是100
        Jack.getInitState();
        Jack.stateDisplay();

        // 保存进度
        RoleStateCareTaker stateAdmin = new RoleStateCareTaker();
        stateAdmin.setMemento(Jack.saveState());

        // 大战boss时, 损耗严重
        Jack.fight();
        Jack.stateDisplay();

        // 恢复之前状态
        Jack.RecoveryState(stateAdmin.getMemento());
        Jack.stateDisplay();
    }

}
