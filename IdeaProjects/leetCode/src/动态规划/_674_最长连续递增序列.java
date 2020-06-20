package 动态规划;

/*
给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。

         

        示例 1:

        输入: [1,3,5,4,7]
        输出: 3
        解释: 最长连续递增序列是 [1,3,5], 长度为3。
        尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
        示例 2:

        输入: [2,2,2,2,2]
        输出: 1
        解释: 最长连续递增序列是 [2], 长度为1。
         

        注意：数组长度不会超过10000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _674_最长连续递增序列 {

    /**
     *
     * 题目比较简单
     *
     * 当 数组为空 或者数组长度==0时, 结果为 0
     *
     * 动态规划 三步走
     *  1，定义状态 cnt为计算 第 i 个元素的 连续递增序列的数量
     *  2, 定义初始值 第一个数 为 1
     *  3, 状态转移方程
     *     cnt = num[i] > nums[i - 1] ? cnt ++ : 1;
     *
     *  max 记录每次计算出来的 cnt 中的 最大值
     *
     * */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int cnt = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                cnt ++;
            }else {
                cnt = 1;
            }

            max = Math.max(max, cnt);
        }
        return max;
    }

}
