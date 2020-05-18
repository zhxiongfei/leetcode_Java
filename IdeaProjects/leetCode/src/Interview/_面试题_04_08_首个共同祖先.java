package Interview;

/*
设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。

        例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]

        3
        / \
        5   1
        / \ / \
        6  2 0  8
        / \
        7   4
        示例 1:

        输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        输出: 3
        解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
        示例 2:

        输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        输出: 5
        解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
        说明:

        所有节点的值都是唯一的。
        p、q 为不同节点且均存在于给定的二叉树中。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/first-common-ancestor-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */

public class _面试题_04_08_首个共同祖先 {

    // 思路
    // 两个节点 p q的 首个公共祖先。有三种情况：要么是root，要么在root的左子树中,要么在root的右子树中
    // 假设递归返回结果为，当root为空，返回null。  或者 root等于p 或者 q时，返回 root。
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果left不为空,说明在左子树中找到了 p 或者 q
        // 如果right不为空,说明在右子树中找到了 p 或者 q

        // left 不为空 且 right不为空时，说明 p q分别在root的左右子树中，所以最终结果为root
        if (left != null && right != null) return root;

        // left不为空 且 right为空， 说明right中不存在p 或者 q。 所以p 和 q都在left中

        // left为空 且 right不为空，说明left中不存在p 或者 q。 说明 p 和 q都在right中
        return left != null ? left : right;
    }
}
