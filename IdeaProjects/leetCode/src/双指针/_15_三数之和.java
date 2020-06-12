package 双指针;

/*
给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。

        注意：答案中不可以包含重复的三元组。

         

        示例：

        给定数组 nums = [-1, 0, 1, 2, -1, -4]，

        满足要求的三元组集合为：
        [
        [-1, 0, 1],
        [-1, -1, 2]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/3sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _15_三数之和 {

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 为避免前边数字重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int num = nums[i];

            int begain = i + 1;
            int end = nums.length - 1;
            while (begain < end){
                // 需要和上一次枚举的数不相同
                if (nums[end] == nums[end - 1]) {
                    continue;
                }

                int l = nums[begain];
                int r = nums[end];

                if (l + r + num == 0){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(num);
                    tmp.add(l);
                    tmp.add(r);
                    res.add(tmp);
                }else if (l + r + num > 0){
                    end --;
                }else {
                    begain ++;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};
        threeSum(nums);
    }

}
