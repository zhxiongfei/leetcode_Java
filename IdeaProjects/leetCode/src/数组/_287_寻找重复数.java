package 数组;

/*
给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。

        示例 1:

        输入: [1,3,4,2,2]
        输出: 2
        示例 2:

        输入: [3,1,3,4,2]
        输出: 3
        说明：

        不能更改原数组（假设数组是只读的）。
        只能使用额外的 O(1) 的空间。
        时间复杂度小于 O(n2) 。
        数组中只有一个重复的数字，但它可能不止重复出现一次。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-the-duplicate-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.time.format.SignStyle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _287_寻找重复数 {

    public static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // 每个元素跟其下标对比 + 1，若不相同， 则交换元素至正确索引
    //https://www.toutiao.com/i6761686631729594894
    public static int findDuplicate1(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1) {
                if (nums[i] == nums[nums[i] - 1]) return nums[i];
                swap(nums,i,nums[i] - 1);
            }
        }

        return -1;
    }

    // Hashset解决
    public static int findDuplicate(int[] nums) {

        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums){
            if (set.contains(num)) return num;
            set.add(num);
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = {8,7,1,10,17,15,18,11,16,9,19,12,5,14,3,4,2,13,18,18};
        int res = findDuplicate(nums);
        if (res != 0){

        }
    }
}
