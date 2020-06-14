package 双指针;

/*
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

         

        示例：

        输入：nums = [-1,2,1,-4], target = 1
        输出：2
        解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
         

        提示：

        3 <= nums.length <= 10^3
        -10^3 <= nums[i] <= 10^3
        -10^4 <= target <= 10^4

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/3sum-closest
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.lang.reflect.Array;
import java.util.Arrays;

public class _16_最接近的三数之和 {

    /**
     *
     * 本题目是 在 15题的基础上稍加改动
     *
     * 15题中，我们采用的 排序 + 双指针的解法
     * 本题中，貌似我们依然可以采用这种解法
     * 只不过在遍历过程中，将和等于 target 的 数依次加入数组
     * 本题中，只要保存最接近的值即可
     * 这样想, 貌似题目还简单了，因为不存在去除麻烦的重复
     *
     * 最接近的
     *   我们先使变量res 为 可能使结果的 数组前三个值的和
     *   遍历排序后数组，头尾双指针的方法
     *   求三个数的和 和 target差值的绝对值
     *   当 sum == target时，差值为0，直接返回 sum
     *   当 sum > target时, right --，找是否有更接近值
     *   当 sum < target时，left ++, 找是否有更接近值
     *
     *   复杂度分析 :
     *      快排 : O(N * log N)
     *      头尾双指针找接近者 : O(N ^ 2)
     *      整体时间复杂度 : O(N ^ 2)
     *
     *   空间复杂度 : O(1)
     *
     * */
    public static int threeSumClosest(int[] nums, int target) {

// 先排序 时间复杂度 O(N * log N)
        Arrays.sort(nums);

        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right){
                int sum = nums[left] + nums[right] + num;
                // 如果有相等的，直接返回0
                if (sum == target) return sum;
                if (Math.abs(sum - target) < Math.abs(res - target))
                    res = sum;

                if (sum > target){
                    right --;
                }else {
                    left ++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-3,-2,-5,3,-4};
        threeSumClosest(nums, -1);
    }
}
