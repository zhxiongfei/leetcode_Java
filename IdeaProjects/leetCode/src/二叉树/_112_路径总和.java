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
        // 根节点为空，直接返回false
        if (root == null) return false;

        // 遍历到 root 时， 将 sum的值 减去 root.val
        sum -= root.val;

        // 此时，如果root为叶子节点， 返回 true， 否则返回false
        if (root.left == null && root.right == null) return sum == 0;
        // 继续分别遍历 root的左右子节点
        // 两个中，有一个为 true 则返回true。 都为false， 则返回false
        return hasPathSumRecursive(root.left,sum) || hasPathSumRecursive(root.right,sum);
    }

    // 迭代
    static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        // 节点栈
        Stack<TreeNode>stack = new Stack<>();
        stack.push(root);
        // 数字栈
        Stack<Integer> numStack = new Stack<>();
        numStack.push(sum - root.val);

        // 当栈不为空时 循环
        while (!stack.isEmpty()){
            // 节点栈 取出节点
            TreeNode node = stack.pop();
            // 数字栈 取出数字
            int num = numStack.pop();
            // 当节点为跟节点， 且 数字为 0时。 符合条件，return true
            if (node.left == null && node.right == null && num == 0) return true;

            // 将 节点的 右/左子节点 以及 num与子节点的差值 分别入栈
            if (node.right != null) {
                stack.push(node.right);
                numStack.push(num - node.right.val);
            }

            if (node.left != null){
                stack.push(node.left);
                numStack.push(num - node.left.val);
            }
        }

        // 遍历完一整棵树，一直没有叶子节点，且 数字为0的情况， 则返回false
        return false;
    }

    public static void main(String[] args){
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);

        boolean res = hasPathSum(node,1);
        System.out.println(res);
    }
}
