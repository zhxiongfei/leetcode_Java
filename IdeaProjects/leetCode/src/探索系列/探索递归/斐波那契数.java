package 探索系列.探索递归;

public class 斐波那契数 {

    public int fib(int N) {
        if (N <= 0) return 0;
        if (N <= 2) return 1;
        return fib(N -1) + fib(N - 2);
    }


    public int fib1(int N) {
        if (N <= 0) return 0;
        if (N <= 2) return 1;

        int[] dp = new int[N];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[N - 1];
    }

    public int fib2(int N) {
        if (N <= 0) return 0;
        if (N <= 2) return 1;

        int first = 1;
        int second = 1;
        for (int i = 2; i < N; i++) {
            int tmp = second;
            second += first;
            first = tmp;
        }

        return second;
    }

}
