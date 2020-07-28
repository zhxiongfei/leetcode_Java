package 设计模式.命令模式;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Waiter {

    private List<Command> orders = new LinkedList<>();

    private Command command;

    // 设置订单
    public void setCommand(Command command) {
        System.out.println(command.toString());
        if (command instanceof BakeChickenWingCommand){
            System.out.println("服务员 : 鸡翅没有了, 请点别的烧烤.");
        }else {
            orders.add(command);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            System.out.println("增加订单: " + command.toString() + " 时间 : " + df.format(new Date()));
        }
        this.command = command;
    }

    // 取消订单
    public void cancelOrder(Command command){
        orders.remove(command);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("取消订单: " + command.toString() + " 时间 " + df.format(new Date()));
    }

    // 通知执行
    public void notifying(){
        for (Command cmd : orders) {
            cmd.executeCommand();
        }
    }

}
