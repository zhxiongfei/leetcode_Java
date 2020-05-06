package 二叉树;

/*
给定一个二叉树，返回它的中序 遍历。

        示例:

        输入: [1,null,2,3]
        1
        \
        2
        /
        3

        输出: [1,3,2]
        进阶: 递归算法很简单，你可以通过迭代算法完成吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _94_二叉树的中序遍历 {

    ArrayList<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversalRevursive(TreeNode root){
        if (root == null) return list;

        inorderTraversalRevursive(root.left);
        list.add(root.val);
        inorderTraversalRevursive(root.right);

        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root){
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null){

            while (node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }

        return list;
    }
}
