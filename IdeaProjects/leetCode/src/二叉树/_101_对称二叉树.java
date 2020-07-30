package 二叉树;

/*
给定一个二叉树，检查它是否是镜像对称的。

        例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

        1
        / \
        2   2
        / \ / \
        3  4 4  3
        但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

        1
        / \
        2   2
        \   \
        3    3
        说明:

        如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/symmetric-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.*;

public class _101_对称二叉树 {

    // 递归
    public boolean isMirror(TreeNode t1,TreeNode t2){
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;

        return (t1.val == t2.val)
                && isMirror(t1.right,t2.left)
                && isMirror(t1.left,t2.right);
    }

    public boolean isSymmetric1(TreeNode root) {

        return isMirror(root,root);
    }

    // 迭代
    public boolean isSymmetric(TreeNode root) {
        Queue q = new LinkedList();
        q.add(root);
        q.add(root);

        while (!q.isEmpty()){
            TreeNode n1 = (TreeNode) q.poll();
            TreeNode n2 = (TreeNode) q.poll();

            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.val != n2.val) return false;

            q.offer(n1.left);
            q.offer(n2.right);
            q.offer(n1.right);
            q.offer(n2.left);
        }
        return true;
    }

    public static void main(String[] args) {
        _101_对称二叉树 cls = new _101_对称二叉树();

        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);

        node.right = new TreeNode(2);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(3);

        boolean res = cls.isSymmetric(node);
        System.out.println(res);
    }
}
