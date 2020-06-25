package 剑指offer;

/*
统计一个数字在排序数组中出现的次数。

         

        示例 1:

        输入: nums = [5,7,7,8,8,10], target = 8
        输出: 2
        示例 2:

        输入: nums = [5,7,7,8,8,10], target = 6
        输出: 0
         

        限制：

        0 <= 数组长度 <= 50000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _剑指_Offer_53_I_在排序数组中查找数字I {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;

        // 二分法找 值为target 的最右边索引
        int right = 0;
        int begain = 0, end = nums.length - 1;
        while (begain <= end){
            int mid = (begain + end) >> 1;
            if (nums[mid] <= target){
                // 往右边找
                begain = mid + 1;
            }else {
                // 往左边找
                end = mid - 1;
            }
        }
        right = begain;
        // 如果没找到
        if(end >= 0 && nums[end] != target) return 0;

        // 二分法找 值为targe 的最左边索引
        int left = 0;
        begain = 0;
        end = nums.length - 1;
        while (begain <= end){
            int mid = (begain + end) >> 1;
            if (nums[mid] >= target){
                // 往左边找
                end = mid - 1;
            }else {
                // 往右边找
                begain = mid + 1;
            }
        }
        left = end;

        return right - left - 1;
    }

    public int search1(int[] nums, int target) {

        // 因为此题目中，数组都为int整数
        // 所以，binarySearch查找 target 右边界，再查找 target - 1的右边界
        // 相减 即可 得到 结果
        return binarySearch(nums, target) - binarySearch(nums, target - 1);
    }

    // 二分查找 右边界
    // 其实就是查找 target 右边 第一个不为 target 的下标
    int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= target)
                i = m + 1;
            else
                j = m - 1;
        }
        return i;
    }

    public static void main(String[] args) {

        int[] nums = {5,7,7,8,8,10};
        int target = 8;

        search(nums, target);
    }

}
