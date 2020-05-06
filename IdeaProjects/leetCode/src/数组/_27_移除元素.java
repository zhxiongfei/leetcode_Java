package 数组;

/*
给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

        不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

        元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

        示例 1:

        给定 nums = [3,2,2,3], val = 3,

        函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

        你不需要考虑数组中超出新长度后面的元素。
        示例 2:

        给定 nums = [0,1,2,2,3,0,4,2], val = 2,

        函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

        注意这五个元素可为任意顺序。

        你不需要考虑数组中超出新长度后面的元素。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-element
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _27_移除元素 {

    public static int removeElement(int[] nums, int val) {

        int length = nums.length;

        for (int i = nums.length - 1; i >= 0; i--) {
            int cur = nums[i];
            if (cur == val){
                for (int j = i; j < nums.length - 1; j++) {
                    nums[j] = nums[j+1];
                }
                length --;
            }
        }

        return length;
    }

    public int removeElements1(int[] nums, int val){
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != val){
                nums[i] = nums[j];
                i ++;
            }
        }

        return i;
    }

    public int binarySearch(int[] nums, int val){
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = (l + r) >> 1;
            if (val == nums[mid]){
                return mid;
            }else if (val > nums[mid]){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        return -1;
    }

    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length - 1,mid = 0;
        while (l <= r){
            mid = (l + r) >> 1;
            if (target == mid){
                return mid;
            }else if (target > nums[mid]){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }

        return mid;
    }

    public static void main(String[] args){

        int[] nums = {3,2,2,3};
        int val = 3;
        removeElement(nums,val);
    }
}
