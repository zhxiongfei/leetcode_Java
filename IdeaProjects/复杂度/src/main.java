import java.util.TimerTask;

public class main {

    public  static  int fib(int n){
        if (n<=1) return n;
        return  fib(n-1) + fib(n - 2);
    }

    public  static  int fib1(int n){
        if (n <= 1) return n;

        int sum = 0;
        int first = 0;
        int second = 1;
        for (int i = 0; i < n-1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

    public  static  int fib2(int n){
        if (n <= 1) return n;

        int sum = 0;
        int first = 0;
        int second = 1;

        for (int i = 0; i < n-1; i++) {
            sum = first + second;
            first = second;
            second = sum;
        }

        return second;
    }

    public static void main(String[] args) {
        // 0 1 1 2 3 5 8 13 21 34 55...
        System.out.print(fib1(60));

        Times.test("fib", new Times.Task() {
            @Override
            public void execute() {
                System.out.print(fib(46));
            }
        });

        Times.test("fib1", new Times.Task() {
            @Override
            public void execute() {
                System.out.print(fib1(46));
            }
        });
    }
}