package 哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
        示例 1：

        输入：[3,2,3]
        输出：[3]
        示例 2：

        输入：nums = [1]
        输出：[1]
        示例 3：

        输入：[1,1,1,3,3,2,2,2]
        输出：[1,2]
         

        提示：

        1 <= nums.length <= 5 * 104
        -109 <= nums[i] <= 109
         

        进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/majority-element-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _229_求众数_II {

    /**
     * 哈希表
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * */
    public List<Integer> majorityElement(int[] nums) {
        HashMap<Integer,Integer>map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)){
                map.put(num,map.get(num) + 1);
            }else {
                map.put(num, 1);
            }
        }
        List<Integer>res = new ArrayList<>();
        for (Integer integer : map.keySet()) {
            if (map.get(integer) > nums.length / 3){
                res.add(integer);
            }
        }
        return res;
    }

    /**
     * 更有的摩尔投票
     * 待实现
     * */

}
