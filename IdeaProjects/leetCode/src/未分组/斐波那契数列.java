package 未分组;

public class 斐波那契数列 {

    // 0 1 2 3 4 5 6  7  8  9  10
    // 1 1 2 3 5 8 13 21 34 55 89
    static int fib1(int n){
        if (n == 1 || n == 0) return 1;

        return fib1(n - 1) + fib1(n - 2);
    }

    static int fib(int n){
        if (n == 1 || n == 0) return 1;

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i -1] + dp[i - 2];

        }

        return dp[n];
    }

    public static void main(String[] args){

        int n = 10000;
//        TimeTool.check("fib1", new TimeTool.Task() {
//            @Override
//            public void execute() {
//                System.out.println(fib1(n));
//            }
//        });

        TimeTool.check("fib", new TimeTool.Task() {
            @Override
            public void execute() {
                System.out.println(fib(n));
            }
        });

        System.out.println(fib(10));
    }

}
