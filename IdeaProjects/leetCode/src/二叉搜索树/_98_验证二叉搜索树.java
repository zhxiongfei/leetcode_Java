package 二叉搜索树;

/*
给定一个二叉树，判断其是否是一个有效的二叉搜索树。

        假设一个二叉搜索树具有如下特征：

        节点的左子树只包含小于当前节点的数。
        节点的右子树只包含大于当前节点的数。
        所有左子树和右子树自身必须也是二叉搜索树。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/validate-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _98_验证二叉搜索树 {


    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        List<Integer> list = new ArrayList<>();

        Stack <TreeNode>stack = new Stack();
        stack.push(root);
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

        return false;
    }


    // 二叉树中序遍历递归
    List<Integer> list = new ArrayList<>();
    public List<Integer> inorder(TreeNode root){

        if (root == null) return list;

        inorder(root.left);
        list.add(root.val);
        inorder(root.right);

        return list;
    }

}
