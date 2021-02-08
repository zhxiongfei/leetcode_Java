package 动态规划;

/**
如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
        例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
        相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
        给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。

        示例 1:
        输入: [1,7,4,9,2,5]
        输出: 6
        解释: 整个序列均为摆动序列。

        示例 2:
        输入: [1,17,5,10,13,15,10,5,16,8]
        输出: 7
        解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。

        示例 3:
        输入: [1,2,3,4,5,6,7,8,9]
        输出: 2
        进阶:
        你能否用 O(n) 时间复杂度完成此题?

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/wiggle-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _376_摆动序列 {
    /**
     * 动态规划
     * dp数组含义 : dp[i]表示包含 第i个元素的摆动序列的长度
     *
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(N)
     * */
    // [1,17,5,10,13,15,10,5,16,8]
    public int wiggleMaxLength1(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int res = 1, length = nums.length;

        int[] dp = new int[length];
        int[] flag = new int[length];
        dp[0] = 1;
        flag[0] = 0;

        for (int i = 1; i < nums.length; i++){
            dp[i] = 1;
            int cur = nums[i];
            int pre = nums[i - 1];
            if (cur == pre) continue;

            flag[i] = cur > pre ? 1 : -1;
            for (int j = 0; j < i; j++) {
                if (flag[j] == -flag[i] || flag[j] == 0){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 贪心
     * */
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;

        int prevdiff = nums[1] - nums[0];
        int ret = prevdiff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prevdiff <= 0) || (diff < 0 && prevdiff >= 0)) {
                ret++;
                prevdiff = diff;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        _376_摆动序列 cls = new _376_摆动序列();
        int[] nums = {1,7,4,9,2,5};
        int res = cls.wiggleMaxLength(nums);
        System.out.println(res);
    }
}
