package 滑动窗口;

import java.util.Deque;
import java.util.LinkedList;

/**
在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。

        示例 1：
        输入：A = [0,1,0], K = 1
        输出：2
        解释：先翻转 A[0]，然后翻转 A[2]。

        示例 2：
        输入：A = [1,1,0], K = 2
        输出：-1
        解释：无论我们怎样翻转大小为 2 的子数组，我们都不能使数组变为 [1,1,1]。

        示例 3：
        输入：A = [0,0,0,1,0,1,1,0], K = 3
        输出：3
        解释：
        翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
        翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
        翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]
         
        提示：
        1 <= A.length <= 30000
        1 <= K <= A.length

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-number-of-k-consecutive-bit-flips
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _995_K_连续位的最小翻转次数 {

//    示例 3：
//    输入：A = [0,0,0,1,0,1,1,0], K = 3
//    输出：3
//    解释：
//    翻转 A[0],A[1],A[2]: A变成 [1,1,1,1,0,1,1,0]
//    翻转 A[4],A[5],A[6]: A变成 [1,1,1,1,1,0,0,0]
//    翻转 A[5],A[6],A[7]: A变成 [1,1,1,1,1,1,1,1]

//    示例 2：
//    输入：A = [1,1,0], K = 2
//    输出：-1


    /**
     * 暴力法
     * 一个直观的思路是，从左到右遍历一遍，遇到数字为 0，那么翻转以该数字为起始的 K 个数字
     *
     * 时间复杂度 : O(N * K)
     * 空间复杂度 : O(1)
     * */
    public int minKBitFlips1(int[] A, int K) {
        int length = A.length, res = 0;
        for (int i = 0; i < (length - K + 1); i++) {
            if (A[i] == 1) continue;
            for (int j = 0; j < K; j++) {
                A[i + j] ^= 1;
            }
            res ++;
        }
        for (int i = 0; i < length; i++) {
            if (A[i] == 0) return -1;
        }
        return res;
    }

    /**
     * 滑动窗口
     * */
    public int minKBitFlips(int[] A, int K) {
        Deque<Integer>queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (queue.size() > 0 && i > queue.peek() + K - 1){
                queue.removeFirst();
            }
            // 1.本来是1， 反转奇数次变为0，所以需要再次反转， 放入队列
            // 2.本来是0， 反转偶数次还是0，所以需要再次反转， 放入队列
            if (queue.size() % 2 == A[i]){
                if (i + K > A.length) return -1;
                queue.add(i);
                res ++;
            }
        }
        return res;
    }
}
