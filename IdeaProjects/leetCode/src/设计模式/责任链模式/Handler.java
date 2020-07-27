package 设计模式.责任链模式;

public abstract class Handler {

    // 继任者
    protected Handler successor;

    // 设置继任者
    public Handler(Handler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(int request);
}
