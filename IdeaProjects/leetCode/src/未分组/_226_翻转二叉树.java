package 未分组;

import com.sun.source.tree.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class _226_翻转二叉树 {

    public TreeNode invertTree1(TreeNode root) {

        if (root == null) return null;

        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;

        invertTree1(root.left);
        invertTree1(root.right);

        return root;
    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) return null;

        Queue <TreeNode>queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;

            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return root;
    }

}
