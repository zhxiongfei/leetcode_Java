package 二叉树;

public class _226_翻转二叉树 {

    public TreeNode invertTree(TreeNode root) {

        if (root == null) return null;

        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
