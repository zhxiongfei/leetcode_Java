package 数组;

/*
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

        你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

        示例:

        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/two-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.HashMap;

public class _1_两数之和 {

    // 暴力法 - 两层循环
    public int[] twoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                int n = nums[j];
                if (m + n == target){
                    int [] res = {m,n};
                    return res;
                }
            }
        }
        return null;
    }

    // 利用hashmap减少查询时间
    /*
    * HashMap中保存{差值 ：index}
    * 遍历， 如果map的key包含 取到的值。则取出i和 key对应的value
    * */
    public int[] twoSum1(int[] nums, int target) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            if (map.containsKey(m)){
                int [] res = {map.get(m),i};
                return res;
            }
            map.put(target - m,i);
        }
        return null;
    }
}
