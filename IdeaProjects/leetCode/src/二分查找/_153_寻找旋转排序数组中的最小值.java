package 二分查找;

/*
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

        ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

        请找出其中最小的元素。

        你可以假设数组中不存在重复元素。

        示例 1:

        输入: [3,4,5,1,2]
        输出: 1
        示例 2:

        输入: [4,5,6,7,0,1,2]
        输出: 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _153_寻找旋转排序数组中的最小值 {

    /**
     *
     * 有序数组，很容易想到二分查找
     * 二分查找可以将 时间复杂度从线性 降低到 对数级
     *
     * 难点在于，如何进行缩小范围的判断？
     *
     * 以下用 mid 来表示 中间下标， begain为左边下标，end为右边下标
     * 当 nums[end] > nums[mid] 时, 说明 [mid, end] 范围内单调递增
     *    说明最小值 在 [begain , mid] 范围内
     *    end = mid
     *
     * 当 nums[end] < nums[mid] 时, 说明 [mid, end] 范围内 不是单调递增
     *    也就是说旋转点在 [mid + 1, end] 范围内
     *    begain = mid + 1;
     *
     *
     * 当 nums[end] == nums[mid] 时, 则 end --。 缩小范围
     *
     * */
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];

        int begain = 0, end = nums.length - 1;
        while (begain < end){
            int mid = begain + ((end - begain) >> 1);

            if (nums[end] > nums[mid]){
                // 在 [begain, mid] 范围内
                end = mid;
            }else if (nums[end] < nums[mid]){
                // 在 [mid + 1, end] 范围内
                begain = mid + 1;
            }else {
                end --;
            }
        }
        return nums[begain];
    }

}
