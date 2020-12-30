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


    // 迭代 和 递归两种方法思路一样， 都是利用二叉树的中序遍历
    // 二叉搜索树的中序遍历结果，是严格升序的， 所以我们中序遍历二叉树
    // 记录前一个遍历的节点，当前节点的值 比 前一个节点小或者相等时， 不满足BST，return false。 否则继续遍历
    // 遍历完毕，没有出现不满足的情况， 则为BST

    // 迭代
    public boolean isValidBST1(TreeNode root) {

        long prev = Long.MIN_VALUE;
        Stack <TreeNode>stack = new Stack<>();
        TreeNode node = root;

        while (!stack.isEmpty() || node != null){

            while (node != null){
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            System.out.println(node.val);
            if (node.val <= prev) return false;
            prev = node.val;
            node = node.right;
        }
        return true;
    }

    // 递归
    long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root){

        if (root == null) return true;

        // 访问左子树
        if (!isValidBST(root.left)) return false;

        // 访问当前节点，如果当前节点的值比前一个节点的值小， 则不满足BST, return false。 否则继续遍历
        if (prev >= root.val) return false;

        // 记录前一个节点的值
        prev = root.val;

        return isValidBST(root.right);
    }

    public static void main(String[] args) {
        _98_验证二叉搜索树 test = new _98_验证二叉搜索树();

        TreeNode node = new TreeNode(0);
        node.left = new TreeNode(-1);

        boolean isBST = test.isValidBST(node);
        if (isBST){

        }
    }

}
