package 前缀和问题;

/*
给你一个整数数组 nums 和一个整数 k。

        如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

        请返回这个数组中「优美子数组」的数目。

         

        示例 1：

        输入：nums = [1,1,2,1,1], k = 3
        输出：2
        解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
        示例 2：

        输入：nums = [2,4,6], k = 1
        输出：0
        解释：数列中不包含任何奇数，所以不存在优美子数组。
        示例 3：

        输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
        输出：16
         

        提示：

        1 <= nums.length <= 50000
        1 <= nums[i] <= 10^5
        1 <= k <= nums.length

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import javax.swing.*;
import java.util.HashMap;

public class _1248_统计_优美子数组 {

    // 计算前缀和数组 arr
    // 双重循环 arr[j] - arr[i] == k 的个数, 时间复杂度 : O(N ^ 2)
    // leetcode提交后，华丽丽的超时了..
    public static int numberOfSubarrays1(int[] nums, int k) {

        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        for (int i = 1; i <= nums.length; i++) {
            int num = nums[i - 1];
            boolean isodd = num % 2 == 1;
            prefix[i] = i == 0 ? (isodd ? 1 : 0) : (isodd ? prefix[i - 1] + 1 : prefix[i - 1]);
        }

        int count = 0;
        for (int i = 0; i < prefix.length; i++) {
            for (int j = i + 1; j < prefix.length; j++) {
                if (prefix[j] - prefix[i]== k) count ++;
            }
        }
        return count;
    }

    // 优化
    // 使用 map 将时间复杂度 优化值 O(N)
    // map中存放 <前缀和(也就是奇数的个数)， 前缀和的个数>
    // prev变量，记录当前遍历的元素前，奇数的个数
    // count变量，记录i j组合的个数
    // 当prev >= k 时, 如果 map 中包含 prev - k. 则 count += map.get(perv - k)
    // 并且，map中重新赋值
    // 此算法时间复杂度 O(N)
    // 空间复杂度 O(N)
    public static int numberOfSubarrays2(int[] nums, int k) {

        // <前缀和，前缀和的个数>
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);

        int prev = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            prev += num & 1;

            if (prev >= k) count += map.get(prev - k);

            map.put(prev,map.getOrDefault(prev,0) + 1);
        }

        return count;
    }

    // 优化
    // map 优化为 数组
    // 思路一样, 只是将 map 改为 数组
    // hashmap在存取值时，需要进行hash运算，也需要更大的存储空间
    // 所以可以用数组实现算法时，比hashmap更优秀
    public static int numberOfSubarrays3(int[] nums, int k) {

        int[] prefixs = new int[nums.length + 1];
        prefixs[0] = 1;

        int prev = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            prev += num & 1;

            if (prev >= k) count += prefixs[prev - k];

            prefixs[prev] = prefixs[prev] + 1;
        }

        return count;
    }

    // 滑动窗口
    public static int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0, res = 0, oddCnt = 0;

        while (right < nums.length){
            if ((nums[right ++] & 1) == 1){
                oddCnt ++;
            }

            // 若当前滑动窗口 [left right) 中有k个奇数了，进入判断统计优美子数组个数。
            if (oddCnt == k){
                // 先将滑动窗口的右边届向右扩展，直到遇到下一个奇数或者出界
                // rightCnt 为第k个奇数 右边偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0){
                    right ++;
                }
                int rightCnt = right - tmp;

                // leftCnt 为第 1 个奇数左边的偶数的个数
                int leftCnt = 0;
                while ((nums[left] & 1) == 0){
                    leftCnt ++;
                    left ++;
                }

                // 第 1 个奇数左边的 leftCnt 个偶数都可以作为 优美子数组的起点
                // 因为 第 1 个奇数可以1个偶数都不取，所以起点的选择有 leftCnt + 1种
                // 第 k 个奇数右边 rightCnt 个偶数都可以作为优美子数组的终点
                // 因为 第 k 个奇数右边可以1个偶数都不取， 所以终点的选择有 rightCnt + 1种
                // 所以该滑动窗口中, 优美子数组 左右起点的选择组合数为 (leftCnt + 1) * (rightCnt + 1)
                res += (leftCnt + 1) * (rightCnt + 1);

                // 此时 left指向的是第一个奇数，因为该区间统计完了，因此left 右移一位， oddCnt --
                left ++;
                oddCnt --;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,2,1,2,1,2};
        numberOfSubarrays(nums, 2);
    }
}
