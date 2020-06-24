package 探索系列.探索初级算法;

import java.util.Stack;

public class 验证二叉搜索树 {

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        double prev = Long.MIN_VALUE;
        while (!stack.isEmpty() || node != null){
            while (node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();

            if (node.val <= prev) return false;
            prev = node.val;

            node = node.right;
        }

        return true;
    }

}
