package 剑指offer;

/*
请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

        例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

            1
           / \
          2   2
         / \ / \
        3  4 4  3
        但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

            1
           / \
          2   2
           \   \
           3    3

         

        示例 1：

        输入：root = [1,2,2,3,4,4,3]
        输出：true
        示例 2：

        输入：root = [1,2,2,null,3,null,3]
        输出：false
         

        限制：

        0 <= 节点个数 <= 1000

        注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题28_对称的二叉树 {

    public boolean isMirror(TreeNode node1, TreeNode node2){
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;

        return isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left) && node1.val == node2.val;
    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root,root);
    }

}
