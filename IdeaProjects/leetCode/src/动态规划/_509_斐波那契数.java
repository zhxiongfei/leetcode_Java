package 动态规划;

public class _509_斐波那契数 {

    // 递归
    public int fib1(int n){
        if (n <= 0) return 0;
        if (n <= 2) return 1;

        return fib1(n - 1) + fib1(n - 2);
    }

    // 动态规划
    public int fib(int n){
        if (n <= 0) return 0;
        if (n <= 2) return 1;

        int num1 = 1;
        int num2 = 1;
        while (n > 2){
            int tmp = num1;
            num1 = num2;
            num2 += tmp;

            n--;
        }
        return num2;
    }
}
