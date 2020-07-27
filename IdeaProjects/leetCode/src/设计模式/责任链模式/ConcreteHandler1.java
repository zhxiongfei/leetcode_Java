package 设计模式.责任链模式;

public class ConcreteHandler1 extends Handler {

    public ConcreteHandler1(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(int request) {
        if (request >= 0 && request < 10) {
            System.out.println(this.getClass().getName() + "  处理请求  " + "request is  " + request);
        }else if (successor != null){
            successor.handleRequest(request);
        }
    }
}
