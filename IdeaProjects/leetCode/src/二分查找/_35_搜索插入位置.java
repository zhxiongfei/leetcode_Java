package 二分查找;

/**
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

        你可以假设数组中无重复元素。

        示例 1:

        输入: [1,3,5,6], 5
        输出: 2
        示例 2:

        输入: [1,3,5,6], 2
        输出: 1
        示例 3:

        输入: [1,3,5,6], 7
        输出: 4
        示例 4:

        输入: [1,3,5,6], 0
        输出: 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/search-insert-position
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _35_搜索插入位置 {

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        if(target > nums[nums.length - 1]) return nums.length;

        int begin = 0, end = nums.length - 1;
        while (begin < end){

            int mid = begin + ((end - begin) >> 1);
            int num = nums[mid];
            if (target == num) return mid;
            if (target > num){
                begin = mid + 1;
            }else {
                end = mid;
            }
        }
        return begin;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,13,14};
        int res = searchInsert(nums, 2);
        System.out.println(res);
    }
}
