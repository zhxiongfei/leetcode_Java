package 剑指offer;

/*
输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

         

        示例 1：

        输入：nums = [2,7,11,15], target = 9
        输出：[2,7] 或者 [7,2]
        示例 2：

        输入：nums = [10,26,30,31,47,60], target = 40
        输出：[10,30] 或者 [30,10]
         

        限制：

        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^6


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/he-wei-sde-liang-ge-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _面试题57_和为s的两个数字 {

    /*
    *
    * 暴力法
    *
    * 用map 存储 <target - num, num>. 遍历下一个元素时，如果 map的key中包含。说明和为target。
    * 遍历完毕，没找到，则返回空
    *
    * 时间复杂度 : O(N)
    * 空间复杂度 : O(N)
    *
    * 和预想的一个，超时了。
    *
    * 因为我们完全没有用到升序数组这个条件
    * 应该可以利用二分的思想 O(log N)时间内解决此题目
    *
    * 试着写了一会代码，发现二分的思想貌似不能用
    *
    * 因为一个最小的数字 + 一个最大的数字。 有可能和刚好为 target
    * 所以二分没办法缩小查找范围。不能用
    *
    * 所以我们利用，双指针法解决
    * 两个指针分别指向数组的开头begain和结尾end
    * 依次计算两个指针指向元素的和
    *   如果刚好等于 target 则为最终结果
    *   如果 大于 target。 则 end --； 继续循环
    *   如果 小于 target。 则 begain ++； 继续循环
    * 环条件 begian < end
    *
    * */
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsValue(target - nums[i])){
                int[] res = new int[2];
                res[0] = target - nums[i];
                res[1] = nums[i];
                return res;
            }
            map.put(target - nums[i], nums[i]);
        }
        return null;
    }

    /*
    *
    * 双指针
    * 时间复杂度 : O(N)
    * 空间复杂度 : O(1)
    *
    * */
    public int[] twoSum(int[] nums, int target) {

        int begain = 0, end = nums.length - 1;
        while (begain < end) {
            int n1 = nums[begain];
            int n2 = nums[end];

            if (n1 + n2 == target) {
                int[] res = new int[2];
                res[0] = n1;
                res[1] = n2;
                return res;
            }

            if (n1 + n2 < target) {
                begain++;
            } else {
                end--;
            }
        }
        return null;
    }
}
