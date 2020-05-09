package 二叉搜索树;

/*
给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 */

public class _700_二叉搜索树中的搜索 {

    // 二叉搜索树性质， 左子树的值都比根节点小， 右子树的值都比根节点大。
    // 当节点值小于 当前节点值时， 查找其左子树。
    // 当节点值大于 当前节点值时， 查找其右子树
    // 相等时 返回

    // 找到最后，不存在则返回 null
    // 迭代
    public TreeNode searchBST1(TreeNode root, int val) {

        while (root != null){
            if (root.val == val) return root;

            if (root.val > val){
                root = root.left;
            }else {
                root = root.right;
            }
        }
        return null;
    }

    // 递归
    public TreeNode searchBST(TreeNode root, int val) {

        if (root == null) return root;

        if (root.val == val) return root;
        if (root.val > val) return searchBST(root.left,val);

        return searchBST(root.right,val);
    }
}
