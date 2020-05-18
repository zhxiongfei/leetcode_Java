package Interview;

/*
实现一个函数，检查一棵二叉树是否为二叉搜索树。

        示例 1:
        输入:
        2
        / \
        1   3
        输出: true
        示例 2:
        输入:
        5
        / \
        1   4
             / \
            3   6
        输出: false
        解释: 输入为: [5,1,4,null,null,3,6]。
             根节点的值为 5 ，但是其右子节点值为 4 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/legal-binary-search-tree-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class  _面试题_04_05_合法二叉搜索树 {

    // 合法的二叉搜索树中序遍历的结果是生序的
    // 所以中序遍历二叉树，看是否是升序即可
    // 变量prev记录前一个中序遍历的节点
    // 当遍历的当前节点的值 <= prev时 不是二叉搜索树
    // 当当前节点的值 > prev时，则当前节点满足二叉搜索树的性质，将prev赋值为当前值
    // 中序遍历完毕，一直是升序，则为二叉搜索树

    // 递归
    long prev = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        // 遍历左子节点
        if (isValidBST(root.left) == false) return false;

        if (root.val <= prev) return false;
        prev = root.val;

        // 遍历右子节点
        return isValidBST(root.right);
    }

    // 迭代
    public boolean isValidBST1(TreeNode root){
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();

            if (root.val <= prev) return false;

            prev = root.val;
            root = root.right;
        }
        return true;
    }
}
