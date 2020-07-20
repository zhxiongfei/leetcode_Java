package 数组;

import com.sun.jdi.event.StepEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。

         

        示例 1:

        输入: [3,0,1]
        输出: 2
        示例 2:

        输入: [9,6,4,2,3,5,7,0,1]
        输出: 8
         

        说明:
        你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/missing-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _268_缺失数字 {

    /**
     *
     * 排序
     * 时间复杂度 : O(n * log n)
     *
     * */
    public static int missingNumber1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) return i;
        }
        return nums.length;
    }

    /**
     *
     * 数学
     *
     * */
    public static int missingNumber2(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return ((nums.length * (1 + nums.length)) >> 1) - sum;
    }

    /**
     *
     * 位运算
     *
     * */
    public static int missingNumber3(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; ++i){
            res ^= nums[i];
            res ^= i;
        }
        return res;
    }

    public static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     *
     * 挪动元素
     * 跟 287 寻找数组中重复元素解法一样
     *
     * 时间复杂度 : O(n)
     *
     * */
    // [9,6,4,2,3,5,7,0,1]
    public static int missingNumber(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= nums.length - 1) continue;
            while (nums[i] != i){
                if (nums[i] >= nums.length - 1) break;
                swap(nums, nums[i], i);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) return i;
        }
        return nums.length;
    }

    public static void main(String[] args) {

        int[] nums = {9,6,4,2,3,5,7,0,1};
        int res = missingNumber(nums);
        System.out.println(res);
    }

}
