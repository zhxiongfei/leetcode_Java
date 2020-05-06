package 二叉树;

/*
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

        说明: 叶子节点是指没有子节点的节点。

        示例: 
        给定如下二叉树，以及目标和 sum = 22，

        5
        / \
        4   8
        /   / \
        11  13  4
        /  \      \
        7    2      1
        返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/path-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _112_路径总和 {

    // 递归
    static boolean hasPathSumRecursive(TreeNode root, int sum) {
        if (root == null) return false;

        sum -= root.val;
        if (root.left == null && root.right == null) return sum == 0;

        return hasPathSumRecursive(root.left,sum) || hasPathSumRecursive(root.right,sum);
    }

    // 迭代
    static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<TreeNode>stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            sum -= node.val;

            if (node.left == null && node.right == null && sum == 0) return true;

            if (node.left != null) stack.push(node.left);
            if (node.right != null) stack.push(node.right);
        }

        return false;
    }

    public static void main(String[] args){
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);

        boolean res = hasPathSum(node,1);
        System.out.println(res);
    }
}
