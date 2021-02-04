package 滑动窗口;

import java.util.regex.Matcher;

/**
给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
        返回仅包含 1 的最长（连续）子数组的长度。

        示例 1：
        输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
        输出：6
        解释：
        [1,1,1,0,0,1,1,1,1,1,1]
        粗体数字从 0 翻转到 1，最长的子数组长度为 6。

        示例 2：
        输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
        输出：10
        解释：
        [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
        粗体数字从 0 翻转到 1，最长的子数组长度为 10。
         
        提示：
        1 <= A.length <= 20000
        0 <= K <= A.length
        A[i] 为 0 或 1 

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1004_最大连续1的个数III {

    /**
     * 滑动窗口
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     */
    // [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 6
    public int longestOnes(int[] A, int K) {
        int left = 0, right = 0, res = 0, zeroCnt = 0, length = A.length;
        while (right < A.length) {
            if (A[right] == 1 || zeroCnt < K) {
                if (A[right] == 0) zeroCnt++;
                right ++;
                res = Math.max(res, right - left);
            }else {
                if (A[left] == 0) zeroCnt --;
                left ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _1004_最大连续1的个数III cls = new _1004_最大连续1的个数III();
        int[] A = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        cls.longestOnes(A, 6);
    }
}

