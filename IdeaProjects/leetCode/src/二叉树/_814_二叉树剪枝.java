package 二叉树;

/**
给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。

        返回移除了所有不包含 1 的子树的原二叉树。

        ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)

        示例1:
        输入: [1,null,0,0,1]
        输出: [1,null,0,null,1]

        解释:
        只有红色节点满足条件“所有不包含 1 的子树”。
        右图为返回的答案。


        示例2:
        输入: [1,0,1,0,0,0,1]
        输出: [1,null,1,null,1]



        示例3:
        输入: [1,1,0,1,1,0,1,0]
        输出: [1,1,0,1,1,null,1]



        说明:

        给定的二叉树最多有 100 个节点。
        每个节点的值只会为 0 或 1 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-pruning
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _814_二叉树剪枝 {

    /**
     *
     * 使用递归解决这个问题
     * containsOne函数来判断 node 为跟节点的子树是否包含 1
     * 其不包含 1 当且仅当 : node的左右孩子为根节点的子树均不包含1， 并且 node 节点本身的值也不为1
     *
     * 如果 node 的左右孩子为根的子树不包含1，就把对应的指针置空。
     *
     * 在递归结束，如果整棵树都不包含1， 返回 null，否则返回原来的根节点.
     *
     * */
    public boolean containsOne(TreeNode root){
        if (root == null) return false;
        boolean l = containsOne(root.left);
        boolean r = containsOne(root.right);
        if (l == false) root.left = null;
        if (r == false) root.right = null;

        return l || r || root.val == 1;
    }

    public TreeNode pruneTree(TreeNode root) {
        return containsOne(root) ? root : null;
    }

}
