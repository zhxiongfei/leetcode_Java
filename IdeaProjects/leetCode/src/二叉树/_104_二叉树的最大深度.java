package 二叉树;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class _104_二叉树的最大深度 {

    // 利用队列
    public int maxDepth1(TreeNode root) {

        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int height = 0;
        while (!q.isEmpty()){

            int size = q.size();
            while (size > 0){
                TreeNode n = q.remove();
                if (n.right != null) q.add(n.right);
                if (n.left != null) q.add(n.left);

                size --;
            }

            height ++;
        }

        return height;
    }

    // 递归
    public int maxDepth(TreeNode root) {

        if (root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }
}
