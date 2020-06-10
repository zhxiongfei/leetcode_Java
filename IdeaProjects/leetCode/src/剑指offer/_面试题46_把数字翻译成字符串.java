package 剑指offer;

/*
给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

         

        示例 1:

        输入: 12258
        输出: 5
        解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

public class _面试题46_把数字翻译成字符串 {

    /**
     *
     * 动态规划
     * 当 num < 10时，只有一个结果
     *
     * 三个步骤
     * 定义状态
     *  dp[i] 表示截止 i 位，把数字翻译成字符串的 个数
     * 定义初始值
     *  dp[0] = 1, dp[1] = 1. 0 或者 1时，只有一个可能
     * 状态转移方程
     *  计算第 i 位，即dp[i] 时
     *      如果 第 i-1位为0，说明 i-1位和第i位合并，仍然是i。
     *      如果 i-1位 + i位 > 25,则没有字母来表示 合并的数组了
     *      所以这两种情况， dp[i] = dp[i - 1]
     *
     *      除开这两种情况
     *      则 dp[i] = dp[i-1] + dp[i-2]; 也就是所有 i-1和i合并的值 和 i-1和i不合并当前情况
     *
     * */
    public int translateNum1(int num) {
        if (num < 10) return 1;

        String string = String.valueOf(num);
        int count = string.length();
        int[] dp = new int[count + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= count; i++) {
            char pre = string.charAt(i - 2);
            char cur = string.charAt(i - 1);
            if (pre == '0' || pre > '2' || (pre == '2' && cur > '5')){
                dp[i] = dp[i - 1];
            }else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[count];
    }

    /**
     *
     * 空间复杂度的优化
     * 上边题解中，我们使用了额外的dp数组存储空间
     * 但是，我们在递推计算dp[i]的过程，只用到了 dp[i-1] 和 dp[i-2]
     * 所以，我们可以将空间复杂度由原来的 O(N) 优化至 O(1)
     *
     * */
    public int translatNum2(int num){
        if (num < 10) return 1;

        String string = String.valueOf(num);
        int count = string.length();
        int a = 1, b = 1;
        for (int i = 2; i <= count; i++) {
            char pre = string.charAt(i - 2);
            char cur = string.charAt(i - 1);

            if (pre == '0' || pre > '2' ||(pre == '2' && cur > '5')){
                a = b;
            }else {
                int tmp = b;
                b += a;
                a = tmp;
            }
        }
        return b;
    }

}
