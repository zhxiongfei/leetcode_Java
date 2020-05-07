package 未分组;

/*
输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

        例如：

        给定二叉树 [3,9,20,null,null,15,7]，

        3
        / \
        9  20
        /  \
        15   7
        返回它的最大深度 3 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.Queue;

public class _二叉树的深度 {

    // 递归
    public int maxDepthRursive(TreeNode root) {
        if(root == null) return 0;

        return Math.max(maxDepthRursive(root.left),maxDepthRursive(root.right)) + 1;
    }

    // 迭代
    public int maxDepth(TreeNode root){
        if (root == null) return 0;

        int height = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){

            int size = queue.size();
            while (size > 0){
                TreeNode node = queue.poll();

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                size --;
            }

            height ++;
        }

        return height;
    }
}
