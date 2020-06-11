package 栈;

/*
给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。

        nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

         

        示例 1:

        输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
        输出: [-1,3,-1]
        解释:
        对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
        对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
        对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
        示例 2:

        输入: nums1 = [2,4], nums2 = [1,2,3,4].
        输出: [3,-1]
        解释:
            对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
        对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
         

        提示：

        nums1和nums2中所有元素是唯一的。
        nums1和nums2 的数组大小都不超过1000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/next-greater-element-i
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;
import java.util.Stack;

public class _496_下一个更大元素I {

    /**
     *
     * 单调栈 + 哈希表
     * 利用单调递减队列
     *
     * 初始化一个 stack， 一个map<数字 : 右边第一个比其大的数字>
     * 遍历nums2
     *    while（当stack 不为空 并且 当前数字 > 栈顶数字)
     *          则当前数字就是栈顶数字右边第一个比其大的元素
     *          map.put(stack.pop(), 当前数字);
     *    当stack 为空   或者 当前数字 < 栈顶数字时(无重复数组，故没有 ==),  将当前数组入栈
     *  遍历完毕后， 则nums2中所有的元素，其后边第一个值都找到了
     *  而nums1 包含于 nums2. 所以根据map就可以取出 nums1在nums2中所有比其大的第一个元素
     *
     * */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums2.length; i++) {
            int num = nums2[i];
            while (!stack.isEmpty() && num > stack.peek()){
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = -1;
            if (map.keySet().contains(nums1[i])){
                res[i] = map.get(nums1[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        nextGreaterElement(nums1, nums2);
    }

}
