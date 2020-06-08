package 二分查找;

/*
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。


        示例 1:

        输入: nums = [-1,0,3,5,9,12], target = 9
        输出: 4
        解释: 9 出现在 nums 中并且下标为 4
        示例 2:

        输入: nums = [-1,0,3,5,9,12], target = 2
        输出: -1
        解释: 2 不存在 nums 中因此返回 -1
         

        提示：

        你可以假设 nums 中的所有元素是不重复的。
        n 将在 [1, 10000]之间。
        nums 的每个元素都将在 [-9999, 9999]之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-search
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _704_二分查找 {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        return searchRecursive(nums,target,0,nums.length - 1);
    }

    // 迭代二分搜索
    public int search(int[] nums, int target, int left, int right){
        while (left <= right){
            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target){
                right = mid - 1;
                continue;
            }
            left = mid + 1;
        }
        return -1;
    }

    // 递归 二分搜索
    public int searchRecursive(int[] nums, int target, int left, int right){
            if (left > right) return -1;

            int mid = (left + right) >> 1;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target)
                return searchRecursive(nums, target, left, mid - 1);

            return searchRecursive(nums, target, mid + 1, right);
    }

    public static void main(String[] args) {
        _704_二分查找 cls = new _704_二分查找();

        int[] nums = {1,2,3,4,5,12,14,16,34,67};
        cls.search(nums,12);
    }
}
