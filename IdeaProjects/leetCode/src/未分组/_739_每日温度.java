package 未分组;

import java.util.Stack;

public class _739_每日温度 {

    public int[] dailyTemperatures(int[] T) {
        int[] dp = new int[T.length];

        dp[T.length - 1] = 0;

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;

            while(true){
                if(T[i] < T[j]){
                    dp[i] = j - i;
                    break;
                }else if(dp[j] == 0){
                    dp[i] = 0;
                    break;
                }

                j = j + dp[j];
            }
        }

        return dp;
    }

    public int[] dailyTemperatures2(int[] T) {
        int[] dp = new int[T.length];

        dp[T.length - 1] = 0;

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            int tmp = T[i];

            if (T[j] > tmp){
                dp[i] = 1;
                continue;
            }

            while(j < T.length - 1 && T[j] <= tmp){
                j ++;
            }

            dp[i] = j == T.length - 1 ? (T[j] > tmp ? j-i : 0) : j-i;
        }

        return dp;
    }

    public int[] dailyTemperatures1(int[] T) {
        int[] res = new int[T.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]){
                res[stack.peek()] = i;
            }

            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        _739_每日温度 test = new _739_每日温度();

        int[] t = {55,38,53,81,61,93,97,32,43,78};
        int[] res = test.dailyTemperatures(t);
        System.out.println(res.toString());
    }
}
