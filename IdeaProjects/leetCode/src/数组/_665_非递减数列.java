package 数组;

/**
给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。

        我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
        示例 1:

        输入: nums = [4,2,3]
        输出: true
        解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
        示例 2:

        输入: nums = [4,2,1]
        输出: false
        解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
         

        说明：

        1 <= n <= 10 ^ 4
        - 10 ^ 5 <= nums[i] <= 10 ^ 5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/non-decreasing-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _665_非递减数列 {

    /**
     *
     * 1, 用 flag 记录是否改变过数字
     * 2, 遍历数组， 如果 当前数字 >= 前一个数字 -> 继续遍历
     * 3, 如果 当前数字 < 前一个数字， 并且 已经改变过数字(flag == true) -> 返回 false
     * 4, 如果 当前数字 > 前一个数字， 并且 。。。。。。。(flag == false) -> 此时， 可以改变 prev 或者 num
     *    如果 num > prevMax， 则把当前数字改为 prev。 （测试用例 [3,4,2,3]）
     *    否则, 把修改 prev。 且flag置为 true。       （测试用例 [1,4,2,3]）
     *
     * */
    public boolean checkPossibility(int[] nums) {
        if (nums.length < 3) return true;
        boolean flag = false;
        int prevMax = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            int prev = nums[i - 1];
            if (prev <= num) {
                prevMax = prev;
                continue;
            }
            if (flag == false){
                flag = true;
                if (prevMax > num) nums[i] = prev;
                continue;
            }
            return false;
        }
        return true;
    }

    // [3,4,2,3]
    // [5,7,1,8]
    public static void main(String[] args) {
        _665_非递减数列 cls = new _665_非递减数列();
        int[] nums = {5,7,8,1};
        boolean res = cls.checkPossibility(nums);
        System.out.println(res);
    }
}
