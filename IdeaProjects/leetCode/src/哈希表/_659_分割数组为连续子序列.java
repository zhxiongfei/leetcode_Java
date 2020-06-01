package 哈希表;

/*
给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。

        如果可以完成上述分割，则返回 true ；否则，返回 false 。

         

        示例 1：

        输入: [1,2,3,3,4,5]
        输出: True
        解释:
        你可以分割出这样两个连续子序列 :
        1, 2, 3
        3, 4, 5
         

        示例 2：

        输入: [1,2,3,3,4,4,5,5]
        输出: True
        解释:
        你可以分割出这样两个连续子序列 :
        1, 2, 3, 4, 5
        3, 4, 5
         

        示例 3：

        输入: [1,2,3,4,4,5]
        输出: False

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

/*
*
* 贪心算法
* 两个哈希表
* 一个用来计数，一个用来记录上尾部元素
* 哈希表counter 用来存储元素出现的次数, couner[n] 代表 n 出现的次数
* 哈希表end 用来存储以元素结尾的连续子序列(指至少包含 三个 连续整数的子序列) 个数, end[n]代表以 n 结尾的连续子序列的个数
*
* */
public class _659_分割数组为连续子序列 {
    public boolean isPossible(int[] nums) {

        HashMap<Integer, Integer> counter = new HashMap<>();
        HashMap<Integer, Integer> tail = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            counter.put(nums[i], counter.getOrDefault(nums[i], 0) + 1);
            tail.put(nums[i], 0);
        }

        for (int i = 0; i < counter.size(); i++) {
            if (counter.get(i) == 0) continue;

            counter.put(i, counter.get(i) - 1);
        }

        return true;
    }
}
