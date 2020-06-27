package 剑指offer;

/*
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

         

        示例 1：

        输入：nums = [4,1,4,6]
        输出：[1,6] 或 [6,1]
        示例 2：

        输入：nums = [1,2,10,4,1,4,3,3]
        输出：[2,10] 或 [10,2]
         

        限制：

        2 <= nums.length <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _剑指_Offer_56_I_数组中数字出现的次数 {


    /**
     *
     * 看到这道题 第一反应是
     * 用set存储遍历过的元素
     * 当遍历过程中
     *  如果set中包含，则说明重复了，将其移出 set
     *  如果set中不包含， 则不将此元素，加入 set
     *
     *
     * 遍历完毕
     * set中存储的两个元素 就是出现一次的数字
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public static int[] singleNumbers(int[] nums) {

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num)){
                set.remove(num);
                continue;
            }
            set.add(num);
        }

        Object[] objects = set.toArray();
        int[] res = new int[2];
        for (int i = 0; i < objects.length; i++) {
            res[i] = (int)objects[i];
        }
        return res;
    }

    /**
     *
     * 但是 题目要求我们
     * 时间复杂度:O(n)
     * 空间复杂度:O(1)
     *
     * 如何不使用额外的存储空间，解决这个问题呢？
     * 接下来，我们尝试做一下空间复杂度的优化
     *
     * 明天开始，将 常见的 位运算整理一遍，再解决这个问题
     *
     * */
    public static int[] singleNumbers2(int[] nums) {

        return null;
    }


    public static void main(String[] args) {

        int[] nums = {4,1,4,6};
        int[] single = singleNumbers(nums);
        if (single == null){

        }
    }
}

