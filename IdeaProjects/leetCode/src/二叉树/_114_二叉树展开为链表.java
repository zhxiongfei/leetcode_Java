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

    // 递归
    // 第一步 左子树变为链表
    // 第二步 右子树变为链表
    // 第三步 原来的左子树 插入到右子树的位置
    // 第四步 清空原来的左子树
    // 第五步 把原来的右子树 插入到原来左子树的最右边
    public void flatten(TreeNode root){
        if (root == null) return;

        // 左子树变为链表
        flatten(root.left);
        // 右子树变为链表
        flatten(root.right);

        // 保存原来的右子树
        TreeNode tmp = root.right;
        // 左子树插入到右子树位置
        root.right = root.left;
        // 清空左子树
        root.left = null;

        // 找到左子树的最右边
        TreeNode pre = root.right;
        while (pre.right != null){
            pre = pre.right;
        }

        // 原来的右子树 插入到左子树的最右边
        pre.right = tmp;
    }

    // 第一步：将左子树 插入到右子树上
    // 第二步：将之前的右子树 插入到之前左子树的最右边节点
    // 第三部：考虑新的右子树的根节点，重复上边的过程
    public void flattern1(TreeNode root){

        while (root != null){
            if (root.left == null){
                // 如果没有左子树，直接处理其右子树
                root = root.right;
                continue;
            }

            // 将原来右子树保存一份
            TreeNode tmp = root.right;

            // 把左子树 插入 到右子树的位置
            root.right = root.left;

            // 清空原来左子树
            root.left = null;

            // 找到原来 左子树的最右边节点
            TreeNode pre = root.right;
            while (pre.right != null){
                pre = pre.right;
            }

            // 原来的右子树 插入到 原来左子树的最右边
            pre.right = tmp;

            root = root.right;
        }
    }
}
