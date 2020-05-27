package 前缀和问题;

/*
给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。

        示例 1 :

        输入:nums = [1,1,1], k = 2
        输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
        说明 :

        数组的长度为 [1, 20,000]。
        数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.awt.*;
import java.util.HashMap;

public class _560_和为K的子数组 {

    // 暴力法
    // 两层循环
    // 思路简单，从头开始遍历, i表示 子数组的开始位置，j表示 子数组的结束位置
    // 当和为 k 时, result++
    // 最终返回 result
    // 时间复杂度 : O(N ^ 2), 其中N为数组的长度
    // 空间复杂度 : O(1)
    // 提交后，算法通过了。 但是用时比较长，时间复杂度高
    public int subarraySum(int[] nums, int k) {

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num == k) count++;

            for (int j = i + 1; j < nums.length; j++) {
                num += nums[j];
                if (num == k) {
                    count ++;
                }
            }
        }
        return count;
    }

    // 优化
    // 前序和
    // 前序和，指的是从第0项直到当前项的总和
    // 如果用数组 prefixSum 表示
    // prefixSum[i] 为数组nums 第 0项，到 第 i 行的总和
    // 所以 nums 第 i 项 到 第 j 项的总和为 prefixSum[j] - prefixSum[i - 1]

    // 题目等价转换
    // 有几种i,j组合，使得从 nums 第i项到第j项的总和为 k
    // 有几种i,j组合, 使得 prefixSum[j] - prefixSum[i - 1] == k
    // 于是求出 prefixSum 数组, 再看那些项相减差值为 k 。统计count
    // 但是仍然需要 i，j两个变量，两层for循环。 时间复杂度仍然是 O(N ^ 2)

    // 引入哈希表
    // 哈希表中存放 <前序和 ： 出现次数>
    // 当遍历到数组某一位置，其前序和 和 k 的差值，在哈希表中存在
    // 则 次数 += map.get(前序和 - 差值)
    // map 中赋值， 如果之前有此前序和， 则 +1。 否则赋值1。

    // 时间复杂度: O(N)
    // 空间复杂度: O(N)
    public static int subarraySum1(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int count = 0, prev = 0;
        for (int i = 0; i < nums.length; i++) {
            prev += nums[i];
            if (map.containsKey(prev - k))
                count += map.get(prev - k);
            map.put(prev,map.getOrDefault(prev, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,7,2,-3,1,4,2,1};
        int result = subarraySum1(nums, 7);
        System.out.println(result);
    }
}
