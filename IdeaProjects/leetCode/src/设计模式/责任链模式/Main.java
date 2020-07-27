package 设计模式.责任链模式;

public class Main {

    public static void main(String[] args) {
        Handler h3 = new ConcreteHandler3(null);
        Handler h2 = new ConcreteHandler2(h3);
        Handler h1 = new ConcreteHandler1(h2);

        int[] requests = {2,5,14,22,18,3,27,20};
        for (int request : requests) {
            h1.handleRequest(request);
        }
    }
}
