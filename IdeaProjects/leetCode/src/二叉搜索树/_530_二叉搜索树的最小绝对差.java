package 二叉搜索树;

import java.awt.*;
import java.util.Stack;

/*
给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

         

        示例：

        输入：

        1
        \
        3
        /
        2

        输出：
        1

        解释：
        最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _530_二叉搜索树的最小绝对差 {

    // 二叉搜索树中序遍历的结果，是升序数组。
    // 中序遍历二叉树， 每一步记录与上一步的差值，取最小差值即为最终结果
    // 此题目根 题目 98 验证二叉搜索树 类似，都是采用二叉树中序遍历的方式实现。
    // 迭代
    public int getMinimumDifference1(TreeNode root){

        // 中序遍历前一个元素
        TreeNode prev = null;
        // 保存最小差值
        int minDiff = Integer.MAX_VALUE;

        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (prev == null)
                minDiff = Integer.MAX_VALUE;
            else
                minDiff = Math.min(root.val - prev.val, minDiff);

            prev = root;

            root = root.right;
        }

        return minDiff;
    }

    // 递归
    TreeNode prev = null;
    int minDiff = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root){
        if (root == null) return Integer.MAX_VALUE;

        // 找到左子树的最小差值
        getMinimumDifference(root.left);

        // 计算当前节点的最小差值
        int val = root.val;
        if (prev == null)
            minDiff = Integer.MAX_VALUE;
        else
            minDiff = Math.min(root.val - prev.val,minDiff);

        prev = root;

        // 找到右子树的最小差值
        getMinimumDifference(root.right);

        return minDiff;
    }

    public static void main(String[] args) {
        _530_二叉搜索树的最小绝对差 test = new _530_二叉搜索树的最小绝对差();

        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(5);
        node.right.left = new TreeNode(3);

        test.getMinimumDifference(node);
    }


}
