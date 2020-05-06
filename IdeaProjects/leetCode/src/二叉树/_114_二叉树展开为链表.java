package 二叉树;

/*
给定一个二叉树，原地将它展开为链表。

        例如，给定二叉树

        1
        / \
        2   5
        / \   \
        3   4   6
        将其展开为：

        1
        \
        2
        \
        3
        \
        4
        \
        5
        \
        6

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.ArrayList;
import java.util.List;

public class _114_二叉树展开为链表 {

    public void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode rightNode = root.right;
        root.right = root.left;

        TreeNode curNode = root;
        while (curNode.right != null){
            curNode = curNode.right;
        }
        curNode.right = rightNode;
        root.left = null;
    }

    public void flatten1(TreeNode root) {
        if (root == null) return;

        TreeNode rightNode = root.right;
        root.right = root.left;
        root.left = null;

        if (root.right != null){
            root.right.right = rightNode;
            flatten(root.right);
        }
    }
}
