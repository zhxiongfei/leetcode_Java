package 二叉树;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。

         

        参考以下这颗二叉搜索树：

        5
        / \
        2   6
        / \
        1   3
        示例 1：

        输入: [1,6,3,2,5]
        输出: false
        示例 2：

        输入: [1,3,2,6,5]
        输出: true

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _剑指Offer33_二叉搜索树的后序遍历序列 {

    // 递归
    // post的遍历顺序为“左右根”，每一层判断左右子树和根节点的大小关系，如果满足则继续向下判断；否则直接返回失败
    // 具体做法是：
    // 1）遍历本层数组，找到比根节点(即数组最后一个数)大的第一个节点，此节点向左都是左子树，此节点向右都是右子树
    // 2）上一步已经把左子树的合法性证实了，只需要证实右子树的合法性，即判断右子树所有值大于根节点。
    //右子树合法则继续下一层判断，否则直接返回false
    public static boolean verifyPostorder(int[] postorder) {
        return verifyHelper(postorder, 0, postorder.length - 1);
    }

    public static boolean verifyHelper(int[] postorder, int i, int j){
        // 只有一个元素
        if (i >= j) return true;

        int p = i;
        while (postorder[p] < postorder[j]) p++;

        int m = p;
        while (postorder[p] > postorder[j]) p ++;

        return (p == j) && verifyHelper(postorder, i, m - 1) && verifyHelper(postorder, m, j - 1);
    }

    /**
     *
     * 单调栈
     *
     * */
    public static boolean verifyPostorder1(int[] postorder){

        // 单调栈使用，单调递增的单调栈
        Deque<Integer> stack = new LinkedList<>();
        // 表示上一个根节点的元素，这里可以把postorder的最后一个元素root看成无穷大节点的左孩子
        int pervElem = Integer.MAX_VALUE;
        // 逆向遍历，就是翻转的先序遍历
        for (int i = postorder.length - 1;i>=0;i--){
            // 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
            if (postorder[i] > pervElem){
                return false;
            }
            while (!stack.isEmpty() && postorder[i] < stack.peek()){
                // 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
                // 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走了
                pervElem = stack.pop();
            }
            // 这个新元素入栈
            stack.push(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 8, 6, 12, 16, 14, 10};
        System.out.println(verifyPostorder1(nums));
    }
}
