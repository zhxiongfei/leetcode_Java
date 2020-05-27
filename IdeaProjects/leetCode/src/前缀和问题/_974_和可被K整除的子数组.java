package 前缀和问题;

/*
给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。

         

        示例：

        输入：A = [4,5,0,-2,-3,1], K = 5
        输出：7
        解释：
        有 7 个子数组满足其元素之和可被 K = 5 整除：
        [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
         

        提示：

        1 <= A.length <= 30000
        -10000 <= A[i] <= 10000
        2 <= K <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _974_和可被K整除的子数组 {

    // 暴力法
    // 两层循环
    // 思路简单，从头开始遍历, i表示 子数组的开始位置，j表示 子数组的结束位置
    // 当和对 k 取余 == 0, result++
    // 最终返回 result
    // 时间复杂度 : O(N ^ 2), 其中N为数组的长度
    // 空间复杂度 : O(1)
    // 代码逻辑没问题， 但是提交后， 超时了
    // 时间复杂度太高
    public int subarraysDivByK1(int[] A, int K) {

        int result = 0;
        for (int i = 0; i < A.length; i++) {
            int n1 = A[i];
            if (n1 % K == 0) result++;

            for (int j = i + 1; j < A.length; j++) {
                int n2 = A[j];
                if (n1 + n2 % K == 0) {
                    result ++;
                }
                n1 += n2;
            }
        }
        return result;
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

    // 上述是 题目 560 的解题步骤
    // 974 和 560 题目非常类似
    // 不同之处在于 取模
    // (P[j] - P[i]) % k == 0
    // 则根据同余定理 P[j] % K == P[i] % K;
    // 所以，本题字典中我们存放， <前序和 % K ： 出现次数>
    public static int subarraysDivByK(int[] A, int K) {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,1);
        int prev = 0, count = 0;
        for (int i = 0; i < A.length; i++) {
            prev += A[i];

            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = prev % K;
            if (map.containsKey(modulus))
                count += map.get(modulus);
            map.put(modulus, map.getOrDefault(modulus, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        subarraysDivByK(nums, 5);
    }
}
