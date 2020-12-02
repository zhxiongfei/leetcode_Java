package 二叉树;

import com.sun.source.tree.Tree;

import java.util.*;

public class _145_二叉树的后序遍历 {

    // 非递归
    public List<Integer> postorderTraversal(TreeNode root) {

        ArrayList<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) return list;

        stack.push(root);
        TreeNode pre = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if ((node.left == null && node.right == null) || (pre != null && (pre == node.left || pre == node.right))) {
                // 叶子节点 或者 上一个访问的节点是此节点的子节点时 出栈
                list.add(node.val);
                pre = node;
                stack.pop();
            } else {
                // 右子节点不为空
                if (node.right != null) stack.push(node.right);
                // 左子节点不为空
                if (node.left != null) stack.push(node.left);
            }
        }

        return list;
    }

    // 递归
    ArrayList<Integer> list = new ArrayList<>();

    public List<Integer> postOrderTraversalRecursive(TreeNode root) {

        if (root == null) return list;
        postOrderTraversalRecursive(root.left);
        postOrderTraversalRecursive(root.right);

        list.add(root.val);

        return list;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int maxDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode node = root;

        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {

                node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                size--;
            }

            height++;
        }

        return height;
    }
}

//            if (node.left != null){
//                    if (node.left.left == null && node.left.right == null){
//                    // left  叶子节点
//                    list.add(node.left.val);
//                    node.left = null;
//                    }else{
//                    // left  非叶子节点
//                    node = node.left;
//                    stack.push(node);
//                    }
//
//                    }else if (node.right != null){
//                    if (node.right.left == null && node.right.right == null){
//                    // right 叶子节点
//                    list.add(node.right.val);
//                    node.right = null;
//                    }else{
//                    // right 非叶子节点
//                    node = node.right;
//                    stack.push(node);
//                    }
//                    }else{
//                    // 根节点
//                    list.add(node.val);
//                    node = null;
//                    }