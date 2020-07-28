package 设计模式.命令模式;

public abstract class Command {

    protected Barbecuer receiver;

    public Command(Barbecuer receiver){
        this.receiver = receiver;
    }

    abstract public void executeCommand();
}
