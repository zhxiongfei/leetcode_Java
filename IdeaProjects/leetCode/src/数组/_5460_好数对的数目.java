package 数组;

import java.util.HashMap;

/**
给你一个整数数组 nums 。

        如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。

        返回好数对的数目。

         

        示例 1：

        输入：nums = [1,2,3,1,1,3]
        输出：4
        解释：有 4 组好数对，分别是 (0,3), (0,4), (3,4), (2,5) ，下标从 0 开始
        示例 2：

        输入：nums = [1,1,1,1]
        输出：6
        解释：数组中的每组数字都是好数对
        示例 3：

        输入：nums = [1,2,3]
        输出：0
         

        提示：

        1 <= nums.length <= 100
        1 <= nums[i] <= 100

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/number-of-good-pairs
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _5460_好数对的数目 {

    /**
     *
     * 先用 map 存储 每个数字出现的个数
     *
     * 遍历 map， 出现次数不为1时。 计算 可出现的好数对次数, 并加到最终结果中
     *     res += (num * (num - 1)) / 2;
     *
     * */
    public static int numIdenticalPairs(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        int res = 0;
        for (Integer num : map.values()) {
            if (num == 1) continue;
            res += (num * (num - 1)) / 2;
        }
        return res;
    }

    /**
     *
     * 使用数组代替 map 提高效率
     *
     * 因为 nums[i] 在 1 <= nums[i] <= 100 之间
     * 所以可以使用 数组来代替 哈希表，存储每个数字出现的次数
     *
     * 数字代替哈希表经常用在, 要存储的 key 个数有限的情况下。 用来提高效率
     * 因为数组可以根据下标实现随机查找, 而哈希表在查找时，虽然时间复杂度也是 O(1), 但是有哈希运算，也使用了更大的内存空间
     * 可以使用数组优化
     *
     * */
    public static int numIdenticalPairs1(int[] nums) {

        int[] counts = new int[101];

        for (int i = 0; i < nums.length; i++) {
            counts[nums[i]] += 1;
        }

        int res = 0;
        for (int num : counts) {
            if (num == 1) continue;
            res += (num * (num - 1)) / 2;
        }
        return res;
    }

    public static void main(String[] args) {

        int[] nums ={1, 2, 3, 1, 1, 3};
        numIdenticalPairs(nums);
    }
}
