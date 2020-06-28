package 每日一题;

/*
给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

         

        示例:

        输入: [1,2,3,4]
        输出: [24,12,8,6]
         

        提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。

        说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。

        进阶：
        你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/product-of-array-except-self
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _6_4_238_除自身以外数组的乘积 {


    /*
    *
    * 这道题似乎非常简单
    * 先把所有元素积算出来
    * for循环遍历某元素时，积 / 当前元素即可
    *
    *
    * 但是题目中，又有要求，不允许使用 除法
    * 增加了这道题目的难度
    *
    *
    * 左右积
    * 比如 [1,2,3,4] 这个数组，以元素2为例
    * 我们可以看出，除2以外的元素积就是 元素2的前缀所有元素的积 * 其后缀所有元素的积
    * 而某一元素前缀积 和 后缀积。 我们都可以通过for循环计算得来
    * 记为L数组 和 R数组。 就可以求除最后的ans数组
    *
    * 时间复杂度 : O(N)  3遍for循环
    * 空间复杂度 : O(N)  2个额外的数组 ，题目中有说明 ，最终的结果数组不算额外空间
    *
    *
    *
    *  进阶
    *  题目中还有一个要求，是否可以在O（1）的空间复杂度 ，解决问题呢？
    *
    *
    * 我们可以直接利用ans数组来存储，某元素的前缀和，即L数组
    * 当我们计算除R数组后，直接把ans中数字 * R元素中对应的数组
    * 这样我们省去了，一个L数组的空间
    *
    * 时间复杂度 : O(N) 仍然是3遍for循环
    * 空间复杂度 : O(N) 从2个额外的存储空间，降低到了1个额外空间，仍是O（N）
    *
    *
    *
    * 最终解决方案
    * 同上述方案一样，我们利用ans数组来存储前缀积
    * 当算第二轮后缀积时，直接求出最终结果存入ans中
    * 这样，我们即没有用到额外的存储空间，也减少了一遍循环
    * 时间复杂度 ： O(N)
    * 空间复杂度 ； O(1)
    *
    * */
    public static int[] productExceptSelf1(int[] nums) {

        int[] L = new int[nums.length];
        int[] R = new int[nums.length];
        int[] ans = new int[nums.length];

        L[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            L[i] = L[i - 1] * nums[i];
        }

        R[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? 1 : L[i - 1];
            int right = i ==  nums.length-1 ? 1 : R[i + 1];
            ans[i] = left * right;
        }
        return ans;
    }

    public static int[] productExceptSelf(int[] nums) {

        int[] ans = new int[nums.length];

        ans[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            ans[i] = ans[i] * R;
            R *= nums[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        productExceptSelf(nums);
    }
}
