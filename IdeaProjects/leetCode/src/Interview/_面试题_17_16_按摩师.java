package Interview;

/*
一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

        注意：本题相对原题稍作改动

         

        示例 1：

        输入： [1,2,3,1]
        输出： 4
        解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
        示例 2：

        输入： [2,7,9,3,1]
        输出： 12
        解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
        示例 3：

        输入： [2,1,4,5,3,1,1,3]
        输出： 12
        解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/the-masseuse-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _面试题_17_16_按摩师 {

    // 动态规划
    // 跟打家劫舍一模一样
    // 定义dp数组，第i为表示，前i次预约能预约的最大时间
    // 状态转移方程
    // dp[i] 为 如果抢 i, 则dp[i] 为 dp[i - 2] + nums[i].
    // 如果不抢 i, 则 dp[i] 为dp[i - 1]
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(N)
    public int massage(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    // 空间复杂度优化
    // 上面的解题过程中，dp数组我们只用到了 i-1 和 i-2两个变量
    // 所以我们尝试使用两个变量记录前值，代替数组
    // 优化空间复杂度
    // 时间复杂度 仍然是 O(N)
    // 空间复杂度 O(1)
    public int massage1(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int first = nums[0];
        int second = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            int tmp = Math.max(second, first + nums[i]);
            first = second;
            second = tmp;
        }
        return second;
    }

    public static boolean validPalindrome(String s) {

        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j && chars[i] == chars[j]){
            i ++;
            j --;
        }

        // 删除左边元素
        if (isValid(chars, i + 1, j)) return true;

        // 删除右边元素
        if (isValid(chars, i, j - 1)) return true;

        return false;
    }

//    [a, b , b, a]
    public static boolean isValid(char[] chars, int i, int j){
        while (i < j){
            if (chars[i ++] != chars[j --]) return false;
        }
        return true;
    }
}