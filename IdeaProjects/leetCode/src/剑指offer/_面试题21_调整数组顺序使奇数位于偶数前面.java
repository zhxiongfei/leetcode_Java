package 剑指offer;

/*
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。

         

        示例：

        输入：nums = [1,2,3,4]
        输出：[1,3,2,4]
        注：[3,1,2,4] 也是正确的答案之一。
         

        提示：

        1 <= nums.length <= 50000
        1 <= nums[i] <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;

public class _面试题21_调整数组顺序使奇数位于偶数前面 {

    /**
     *
     * 暴力解法：
     * 使用两个临时数组
     * 一个存放偶数，一个存放奇数
     * 最终把两个数据合并得到最终结果
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public int[] exchange(int[] nums) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num % 2 == 0)
                list2.add(num);
            else
                list1.add(num);
        }

        list1.addAll(list2);

        int[] ans = new int[list1.size()];
        for (int i = 0; i < list1.size(); i++) {
            ans[i] = list1.get(i);
        }
        return ans;
    }

    /**
     *
     * 双指针
     * 头指针 left, 尾指针 right
     * left一直往右移，直到它指向的值为偶数
     * right一直往左移，直到它指向的数为奇数
     * 交换 left 和 right位置的元素，并且left右移，right左移
     *
     * */
    public void swap(int[] nums, int left ,int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public int[] exchange1(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right){
            if (nums[left] % 2 == 1){
                left ++;
                continue;
            }
            if (nums[right] % 2 == 0){
                right --;
                continue;
            }

            swap(nums, left ++, right --);
        }
        return nums;
    }

    /**
     *
     * 快慢指针
     * 用两个指针，
     * slow 指向最后一个偶数
     * fast 遍历数组
     * 当fast指向的数字是奇数时
     * 交换 slow 和 fast的元素 并且 slow ++
     * */
    public int[] exchange2(int[] nums){
        int slow = 0, fast = 0;
        while (fast < nums.length){
            if (nums[fast] % 2 == 1)
                // 奇数时
                swap(nums, slow ++, fast);
            fast ++;
        }
        return nums;
    }
}
