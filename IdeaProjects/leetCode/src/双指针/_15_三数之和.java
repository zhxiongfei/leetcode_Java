package 双指针;

/*
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

        注意：答案中不可以包含重复的三元组。

         

        示例：

        给定数组 nums = [-1, 0, 1, 2, -1, -4]，

        满足要求的三元组集合为：
        [
        [-1, 0, 1],
        [-1, -1, 2]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/3sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_三数之和 {

    /**
     *
     * 暴力法
     * 三层循环
     * 找到每一种可能， 和为0， 添加到结果数组
     *
     * 时间复杂度 : O(n ^ 3)
     * 空间复杂度 : O(1)
     *
     * */



    /**
     *
     * 先排序
     * 排序后，数组两层循环
     * 取到最外层某个数num
     *    开始计算其后边 n - 1个元素的和为 -num 的组合
     *    后边的操作 类似 排序版本 的两数之和
     *    头尾双指针begain, end，
     *      当和 == -num时, 加入结果数组
     *      当和 < -num时, begain ++
     *      当和 > -num时, end --
     *
     *
     * */
    public static List<List<Integer>> threeSum(int[] nums) {

        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        // 排序以后的操作 跟 排序后的两数之和一样
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {  // 跳过可能重复的答案

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) l++;
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        while (l < r && nums[l] == nums[l + 1]) l++;   // 跳过重复值
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) r--;
                        r--;
                    }
                }
            }
        }
        return ls;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }

}
