package 剑指offer;

/*
在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

         

        示例 1：

        输入：nums = [3,4,3,3]
        输出：4
        示例 2：

        输入：nums = [9,1,7,9,7,9,7]
        输出：1
         

        限制：

        1 <= nums.length <= 10000
        1 <= nums[i] < 2^31

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _剑指_Offer_56_II_数组中数字出现的次数II {

    /**
     *
     * 暴力法
     * for循环 nums数组
     *  map中 存储 <num, count>
     *
     *  遍历 map
     *  当 key 对应的value 为 1时， return key
     *
     *  时间复杂度 : O(N)
     *  空间复杂度 : O(N)
     *
     * */
    public int singleNumber(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        for (Integer key : map.keySet()) {
            if (map.get(key) == 1) return key;
        }

        return -1;
    }

    /**
     *
     * 位运算的思路
     * 暂时搁置
     *
     * */

}
