package 二分查找;

/*
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

        你的算法时间复杂度必须是 O(log n) 级别。

        如果数组中不存在目标值，返回 [-1, -1]。

        示例 1:

        输入: nums = [5,7,7,8,8,10], target = 8
        输出: [3,4]
        示例 2:

        输入: nums = [5,7,7,8,8,10], target = 6
        输出: [-1,-1]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _34_在排序数组中查找元素的第一个和最后一个位置 {

    public static int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0) return new int[]{-1,-1};

        // 二分法找 值为target 的最右边索引
        int begin = 0, end = nums.length - 1;
        while (begin <= end){
            int mid = (begin + end) >> 1;
            if (nums[mid] <= target){
                // 往右边找
                begin = mid + 1;
            }else {
                // 往左边找
                end = mid - 1;
            }
        }
        // 如果没找到
        if (end < 0 || nums[end] != target) return new int[]{-1,-1};
        int right = begin;

        // 二分法找 值为target 的最左边索引
        int left = 0;
        begin = 0;
        end = nums.length - 1;
        while (begin <= end){
            int mid = (begin + end) >> 1;
            if (nums[mid] >= target){
                // 往左边找
                end = mid - 1;
            }else {
                // 往右边找
                begin = mid + 1;
            }
        }
        left = end;

        int[] res = new int[2];
        res[0] = left + 1;
        res[1] = right - 1;
        return res;
    }

    public static int[] searchRange1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1,-1};

        int right = binarySearch(nums, target);
        int left = binarySearch(nums, target - 1);

        return new int[]{left + 1, right - 1};
    }

    // 找右边界
    public static int binarySearch(int[] nums, int target){
        int begain = 0, end = nums.length - 1;
        while (begain <= end){
            int mid = (begain + end) >> 1;
            if (nums[mid] <= target){
                begain = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return begain;
    }

    public static void main(String[] args) {

        int[] nums = {1};
        searchRange(nums, 0);

    }

}
