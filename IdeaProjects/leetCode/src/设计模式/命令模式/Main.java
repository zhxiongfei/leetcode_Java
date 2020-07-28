package 设计模式.命令模式;

public class Main {

    public static void main(String[] args) {

        Barbecuer boy = new Barbecuer();
        Command bakeMuttonCommand1 = new BakeMuttonCommand(boy);
        Command bakeMuttonCommand2 = new BakeMuttonCommand(boy);
        Command bakeChickenWingsCommand1 = new BakeChickenWingCommand(boy);

        Waiter girl = new Waiter();

        girl.setCommand(bakeMuttonCommand1);
        girl.notifying();

        girl.setCommand(bakeMuttonCommand2);
        girl.notifying();

        girl.setCommand(bakeChickenWingsCommand1);
        girl.notifying();
    }

}
