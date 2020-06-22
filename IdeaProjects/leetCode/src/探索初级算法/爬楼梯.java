package 探索初级算法;

public class 爬楼梯 {

    public int climbStairs(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;

        int first = 1;
        int second = 1;
        for (int i = 3; i <= n; i++) {
            int tmp = second;
            second += first;
            first = tmp;
        }
        return second;
    }

}
