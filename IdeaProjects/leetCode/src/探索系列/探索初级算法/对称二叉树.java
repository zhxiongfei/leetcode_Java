package 探索系列.探索初级算法;


public class 对称二叉树 {

    public boolean isSame(TreeNode left, TreeNode right){
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val && (isSame(left.right, right.left)) && (isSame(left.left, right.right));
    }

    public boolean isSymmetric(TreeNode root) {
        return isSame(root,root);
    }

}
