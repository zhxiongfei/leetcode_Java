package 设计模式.责任链模式;

public class ConcreteHandler2 extends Handler {
    public ConcreteHandler2(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(int request) {
        if (request >= 10 && request < 20){
            System.out.println(this.getClass().getName() + "  处理请求  " + "request is  " + request);
        }else if (successor != null){
            successor.handleRequest(request);
        }
    }
}
