package 数组;

/*
给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。

        函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

        说明:

        返回的下标值（index1 和 index2）不是从零开始的。
        你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
        示例:

        输入: numbers = [2, 7, 11, 15], target = 9
        输出: [1,2]
        解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _167_两数之和II_输入有序数组 {

    /**
     *
     * 暴力法，两层循环
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(1)
     *
     * 代码逻辑简单，粗暴
     *
     * */
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int m = numbers[i];
            for (int j = i+1; j < numbers.length; j++) {
                int n = numbers[j];

                if (m + n == target){
                    int [] res = {m + 1,n + 1};
                    return res;
                }
            }
        }

        return null;
    }



    /**
     *
     * 字典存储遍历过的值
     *
     * 用map 存储 <target - num, num>. 遍历下一个元素时，如果 map的key中包含。说明和为target。
     * 遍历完毕，没找到，则返回空
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public int[] twoSum1(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            if (map.containsKey(num)) {
                int[] res = {map.get(num),i};
                return res;
            }
            map.put(target-numbers[i], num);
        }
        return null;
    }

    /**
     * 因为我们完全没有用到升序数组这个条件
     * 因为时有序数组，所以想到使用二分的思想解决问题
     *
     * 但是试着写了一会代码，发现二分的思想貌似不能用
     * 因为一个最小的数字 + 一个最大的数字。 有可能和刚好为 target
     * 所以二分没办法缩小查找范围。不能用
     *
     * 所以我们利用，双指针法解决
     * 两个指针分别指向数组的开头begain和结尾end
     * 依次计算两个指针指向元素的和
     *   如果刚好等于 target 则为最终结果
     *   如果 大于 target。 则 end --； 继续循环
     *   如果 小于 target。 则 begain ++； 继续循环
     *   循环条件 begian < end
     */
    public int[] twoSum2(int[] numbers, int target) {
        int begain = 0, end = numbers.length - 1;
        while (begain < end){
            int num1 = numbers[begain];
            int num2 = numbers[end];

            if (num1 + num2 == target) {
                int[] res = {begain, end};
                return res;
            }

            if (num1 + num2 > target)
                end --;
            else
                begain ++;
        }

        return null;
    }


}
