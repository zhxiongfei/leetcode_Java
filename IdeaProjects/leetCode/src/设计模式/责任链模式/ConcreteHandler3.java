package 设计模式.责任链模式;

public class ConcreteHandler3 extends Handler {

    public ConcreteHandler3(Handler successor) {
        super(successor);
    }

    @Override
    public void handleRequest(int request) {
        if (request >= 20 && request < 30){
            System.out.println(this.getClass().getName() + "  处理请求  " + "request is  " + request);
        }else if (successor != null){
            successor.handleRequest(request);
        }
    }

}
