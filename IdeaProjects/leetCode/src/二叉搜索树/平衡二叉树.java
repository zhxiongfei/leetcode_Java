package 二叉搜索树;

/*
给定一个二叉树，判断它是否是高度平衡的二叉树。

        本题中，一棵高度平衡二叉树定义为：

        一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

        示例 1:

        给定二叉树 [3,9,20,null,null,15,7]

        3
        / \
        9  20
        /  \
        15   7
        返回 true 。

        示例 2:

        给定二叉树 [1,2,2,3,3,null,null,4,4]

        1
        / \
        2   2
        / \
        3   3
        / \
        4   4
        返回 false 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/balanced-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 平衡二叉树 {

    // 计算node节点高度
    // 递归
    public int height(TreeNode node){
        if (node == null) return 0;

        return Math.max(height(node.left),height(node.right)) + 1;
    }

    // 计算节点高度
    // 迭代
    public int height1(TreeNode node){
        if (node == null) return 0;

        int height = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size > 0){
                TreeNode n = queue.remove();
                if (n.left != null) queue.add(n.left);
                if (n.right != null) queue.add(n.right);

                size --;
            }
            height ++;
        }

        return height;
    }

    // 自底向上的递归
    private int recur(TreeNode root){
        if (root == null) return 0;

        int left = recur(root.left);
        if (left == -1) return -1;

        int right = recur(root.right);
        if (right == -1) return -1;

        return Math.abs(left -right) < 2 ? Math.max(left,right) + 1 : -1;
    }

    public boolean isBalanced(TreeNode node) {
        return recur(node) != -1;
    }
}
