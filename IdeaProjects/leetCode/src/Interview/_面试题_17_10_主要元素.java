package Interview;

/*
数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。

        示例 1：

        输入：[1,2,5,9,5,9,5,5,5]
        输出：5
         

        示例 2：

        输入：[3,2]
        输出：-1
         

        示例 3：

        输入：[2,2,1,1,1,2,2]
        输出：2
         

        说明：
        你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-majority-element-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import tools.Asserts;
import 未分组.TimeTool;

import java.util.HashMap;

public class _面试题_17_10_主要元素{

    /*
    *
    * 这道题第一反应是遍历数组
    * 用map存储每个数字出现的次数
    *
    * 当 次数 > half时， 则为主要元素
    * 遍历到 最终，没有 > half 的， return -1
    *
    * 时间复杂度 : O(n)
    * 空间复杂度 : O(n)
    *
    * 但是，明显不符合题目要求
    * 题目中要求 时间:O(N), 空间O(1)
    *
    * 接下来，我们尝试使用 O(1) 的空间复杂度解决
    * */
    HashMap<Integer,Integer> map = new HashMap<>();
    public int majorityElement(int[] nums) {
        int count = nums.length;
        int half = (count >> 1);
        for (int i = 0; i < count; i++) {
            int cnt = map.getOrDefault(nums[i], 0);
            if (++cnt > half) return nums[i];

            map.put(nums[i], cnt);
        }
        return -1;
    }

    // 摩尔投票
    public int majorityElement2(int[] nums) {
        int count = 1;
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != tmp){
                count --;
                if (count < 0){
                    count = 1;
                    tmp = nums[i];
                }
            }else {
                count ++;
            }
        }
        return count > 0 ? tmp : -1;
    }

    // 分治策略
    // 分别计算 左右的众数
    // 如果左右众数一样， 则为最终结果
    // 如果不一样， 则分别在左右计算，众数出现次数。出现次数多者为最终结果
    public int majorityElement1(int[] nums){

        return majority(nums, 0, nums.length);
    }

    public int majority(int[] nums, int begain , int end){
        if (end - begain < 2) return nums[begain];

        int mid = (begain + end) >> 1;

        int left = majority(nums, begain, mid);
        int right = majority(nums, mid, end);

        if (left == right) return left;
        int lcnt = countInRange(nums,left,begain,end);
        int rcnt = countInRange(nums,right,begain,end);
        return lcnt > rcnt ? left : right;
    }

    public int countInRange(int[] nums, int num, int begain , int end){
        int count = 0;
        for (int i = begain; i < end; i++) {
            if (nums[i] == num) count ++;
        }
        return count;
    }

    public static void main(String[] args) {
        _面试题_17_10_主要元素 cls = new _面试题_17_10_主要元素();

    }
}
