package 二分查找;

/**
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

        搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

        你可以假设数组中不存在重复的元素。

        你的算法时间复杂度必须是 O(log n) 级别。

        示例 1:

        输入: nums = [4,5,6,7,0,1,2], target = 0
        输出: 4
        示例 2:

        输入: nums = [4,5,6,7,0,1,2], target = 3
        输出: -1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

public class _33_搜索旋转排序数组 {

    /**
     *
     * 搜索旋转数组,最小值的下标
     * 根据下标，把旋转数组分为两个有序数组
     *
     * */
    public int findMinIdx(int [] nums){
        if (nums.length == 1) return 0;

        int begain = 0, end = nums.length - 1;
        while (begain < end){
            int mid = begain + ((end - begain) >> 1);
            int midNum = nums[mid];

            if (nums[end] > midNum){
                // 说明 [mid, end] 递增
                // 往左边找
                end = mid;
            }else if (nums[end] < midNum){
                // 说明 [midNum, end] 之间不是单调递增
                // 往右边找
                begain = mid + 1;
            }else {
                end --;
            }
        }
        return begain;
    }

    public int binarySearch(int[] nums, int begain, int end, int target){
        if (begain >= end) return (nums[begain] == target) ? begain : -1;

        int mid = begain + ((end - begain) >> 1);
        if (nums[mid] == target) return mid;
        if (nums[mid] > target) return binarySearch(nums, begain, mid - 1, target);
        return binarySearch(nums, mid + 1, end, target);
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx]) return minIdx;
        if (target < nums[minIdx]) return -1;
        // 在后边数组中 [minIdx + 1, end] 之间找
        if (target <= nums[nums.length - 1]) return binarySearch(nums, minIdx + 1, nums.length - 1, target);
        // 在前边数组中 [0, minIdx - 1] 之间找
        return binarySearch(nums, 0, minIdx - 1, target);
    }

    /**
     * 迭代
     * */
    public int search1(int[] nums, int target) {
        int pivot = findMinIdx(nums);
        int begin = 0, end = nums.length - 1;
        if(target > nums[nums.length - 1]){
            end = pivot - 1;
        }else{
            begin = pivot;
        }
        while (begin < end){
            int mid = begin + ((end - begin) >> 1);
            if (nums[mid] == target) return mid;
            if (nums[mid] > target){
                end = mid - 1;
            }else{
                begin = mid + 1;
            }
        }
        return (nums[begin] == target ? begin : -1);
    }

    public static void main(String[] args) {
        _33_搜索旋转排序数组 cls = new _33_搜索旋转排序数组();

        int[] nums = {1,1,1,1,1,2,1,1,1};;
        int target = 2;
        System.out.println(cls.search(nums, target));
    }

}
