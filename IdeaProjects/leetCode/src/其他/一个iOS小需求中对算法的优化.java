package 其他;

import Interview._面试题_17_10_主要元素;
import 未分组.TimeTool;

public class 一个iOS小需求中对算法的优化 {


    // 动态规划
    public static void print(int num){

        int[] dp = new int[num];
        boolean flag = true;
        dp[0] = 0;
        for (int i = 1; i < num; i++) {
            dp[i] = dp[i - 1] + (flag ? 3 : 5);
            flag = !flag;
            System.out.println(dp[i]);
        }
    }

    // 递归
    private static boolean isright(int num){
        if (num < 3 && num != 0) return false;
        if (num == 0 || num == 3) return true;
        return isright(num - 8) && (isright(num - 3) || isright(num - 5));
    }

    // 迭代
    private static boolean isright1(int num){
        if (num < 3 && num != 0) return false;
        if (num == 0 || num == 3) return true;

        int prev = 8;
        boolean flag = true;
        while (prev < num){

            prev += flag ? 3 : 5;
            flag = !flag;
        }
        return prev == num;
    }


    // 取余
    private static boolean isright2(int num){
        return num%8 == 0 || num%8 == 3;
    }

    public static void main(String[] args) {
        _面试题_17_10_主要元素 cls = new _面试题_17_10_主要元素();

        TimeTool.check("递归", new TimeTool.Task() {
            @Override
            public void execute() {
                isright(195);
            }
        });

        int num = 2000000000;
        TimeTool.check("迭代", new TimeTool.Task() {
            @Override
            public void execute() {
                isright1( num);
            }
        });

        TimeTool.check("取余", new TimeTool.Task() {
            @Override
            public void execute() {
                isright2(num);
            }
        });
    }
}
