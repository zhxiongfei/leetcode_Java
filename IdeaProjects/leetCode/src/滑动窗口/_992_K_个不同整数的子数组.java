package 滑动窗口;

import java.util.HashMap;

/**
给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
        （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
        返回 A 中好子数组的数目。

        示例 1：
        输入：A = [1,2,1,2,3], K = 2
        输出：7
        解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].

        示例 2：
        输入：A = [1,2,1,3,4], K = 3
        输出：3
        解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
         
        提示：
        1 <= A.length <= 20000
        1 <= A[i] <= A.length
        1 <= K <= A.length

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _992_K_个不同整数的子数组 {

    // A = [1,2,1,3,4], K = 3
    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostWithDistinct(A, K) - atMostWithDistinct(A, K - 1);
    }

    /**
     * @param A
     * @param k
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostWithDistinct(int[] A, int k){
        int len = A.length;
        int[] freq = new int[len + 1];

        int left = 0, right = 0, count = 0, res = 0;
        while (right < len){
            if (freq[A[right]] == 0){
                count ++;
            }
            freq[A[right]] ++;
            right++;

            while (count > k){
                freq[A[left]]--;
                if (freq[A[left]] == 0){
                    count --;
                }
                left++;
            }
            res += right - left;
        }
        return res;
    }

    public static void main(String[] args) {
        _992_K_个不同整数的子数组 cls = new _992_K_个不同整数的子数组();
        int[] nums = {1,2,1,3,4};
        int res = cls.subarraysWithKDistinct(nums, 3);
        System.out.println(res);
    }
}
