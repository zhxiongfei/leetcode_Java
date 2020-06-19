package 探索头条;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 三数之和 {

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
     *  在确定此题目的解题思路后
     *  本题的难点，在于 如何去除 重复解
     *
     *  我没写好，copy的评论区的代码.
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


}
