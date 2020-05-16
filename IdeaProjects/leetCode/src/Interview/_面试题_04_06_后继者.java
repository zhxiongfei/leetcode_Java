package Interview;

/*
设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。

        如果指定节点没有对应的“下一个”节点，则返回null。

        示例 1:

        输入: root = [2,1,3], p = 1

        2
        / \
        1   3

        输出: 2
        示例 2:

        输入: root = [5,3,6,2,4,null,null,1], p = 6

        5
        / \
        3   6
        / \
        2   4
        /
        1

        输出: null

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/successor-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题_04_06_后继者 {

    // 思路
    // 当p节点有右子树时， 则其后继元素时p节点的右子树的最左子节点
    // 当p节点没有左子树树，需中序遍历树，找到其后继节点
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
//        // 有右子节点时，后继元素是当前元素右子节点 最左子节点
        if (p.right != null) {
            p = p.right;
            while (p.left != null) p = p.left;
            return p;
        }

        // 没有右子节点时，后继元素时当前元素的父节点
        Stack<TreeNode> stack = new Stack<>();
        boolean isNext = false;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (isNext) return root;
            if (root == p) isNext = true;

            root = root.right;
        }
        return null;
    }

    boolean isnext = false;
    TreeNode resnode = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return resnode;

        inorderSuccessor(root.left,p);

        if (isnext) {
            resnode = root;
            isnext = false;
        }
        if (root == p) isnext = true;

        inorderSuccessor(root.right,p);
        return resnode;
    }
}