package 双指针;

/*
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

        注意：

        答案中不可以包含重复的四元组。

        示例：

        给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

        满足要求的四元组集合为：
        [
        [-1,  0, 0, 1],
        [-2, -1, 1, 2],
        [-2,  0, 0, 2]
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/4sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _18_四数之和 {

    /**
     *
     * 其实跟三数之和相比，也没啥新意了
     *
     * 一样的套路，先排序
     * 无非是 内部两层循环，还是三层循环问题
     * 但是做过 三数之和后，都直到去重复以及边界条件的恶心
     *
     * 去重复 去的想吐...
     *
     * */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        // 先排序
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        // 排序以后的操作 跟 排序后的两数之和一样
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int l = j + 1, r = nums.length - 1, sum = -nums[i] - nums[j];
                while (l < r){
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i],nums[j], nums[l], nums[r]));
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
