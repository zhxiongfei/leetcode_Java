package 二叉树;
import java.util.*;

public class _144_二叉树的前序遍历 {

    // 非递归
    public List<Integer> preorderTraversal (TreeNode root) {
        ArrayList <Integer>list = new ArrayList<>();
        if (root == null) return list;

        Stack <TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        while (!stack.isEmpty()){

            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }

        return list;
    }

    ArrayList <Integer>list = new ArrayList<>();
    // 递归
    public List<Integer> preorderTraversalRecursive (TreeNode root) {

        if (root == null) return list;
        list.add(root.val);
        preorderTraversalRecursive(root.left);
        preorderTraversalRecursive(root.right);

        return list;
    }

    public List<Integer> preorderTransversalRecursive1(TreeNode root){
        if (root == null) return list;

        Stack <TreeNode>stack = new Stack();
        stack.push(root);

        while (!stack.isEmpty()){

            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null){
                stack.push(node.right);
            }

            if (node.left != null){
                stack.push(node.left);
            }
        }
        return list;
    }
}
