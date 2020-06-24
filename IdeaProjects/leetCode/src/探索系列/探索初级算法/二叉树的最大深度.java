package 探索系列.探索初级算法;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的最大深度 {

    /**
     *
     * 递归
     *
     * */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     *
     * 迭代
     * 层序遍历
     *
     * */
    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int height = 0;
        while (!queue.isEmpty()){
            int size = queue.size();

            while (size -- > 0){
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            height ++;
        }

        return height;
    }
}
