package 数组;

import java.util.Arrays;

/**
给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。

        请你计算并返回该式的最大值。

         

        示例 1：

        输入：nums = [3,4,5,2]
        输出：12
        解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
        示例 2：

        输入：nums = [1,5,4,5]
        输出：16
        解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
        示例 3：

        输入：nums = [3,7]
        输出：12
         

        提示：

        2 <= nums.length <= 500
        1 <= nums[i] <= 10^3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-product-of-two-elements-in-an-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1464_数组中两元素的最大乘积 {

    /**
     *
     * 先排序
     * 再找最大的两个元素算乘积
     * 时间复杂度 : O(n * log n)
     * */
    public int maxProduct1(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        Arrays.sort(nums);
        return (nums[nums.length - 1] - 1) * (nums[nums.length - 2] - 1);
    }

    /**
     *
     * 不排序
     * 时间复杂度 : O(n)
     *
     * */
    public static int maxProduct(int[] nums) {

        if (nums == null || nums.length <= 1) return 0;
        int m = nums[0], n = nums[1];
        for (int i = 2; i < nums.length; i++) {
            if (m > n){
                if (nums[i] > n) n = nums[i];
            }else {
                if (nums[i] > m) m = nums[i];
            }
        }
        return (m - 1) * (n - 1);
    }

    public static void main(String[] args) {
        int[] nums = {1,5,1,1};
        int res = maxProduct(nums);
        System.out.println(res);
    }
}
