package 二叉树;

/*
给定一个二叉树，计算整个树的坡度。

        一个树的节点的坡度定义即为，该节点左子树的结点之和和右子树结点之和的差的绝对值。空结点的的坡度是0。

        整个树的坡度就是其所有节点的坡度之和。

        示例:

        输入:
        1
        /   \
        2     3
        输出: 1
        解释:
        结点的坡度 2 : 0
        结点的坡度 3 : 0
        结点的坡度 1 : |2-3| = 1
        树的坡度 : 0 + 0 + 1 = 1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-tilt
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _563_二叉树的坡度 {

    int res = 0;
    public int traverse(TreeNode root){
        if (root == null) return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);

        res += Math.abs(left - right);
        return left + right + root.val;
    }

    public int findTilt(TreeNode root) {
        if (root == null) return 0;

        traverse(root);
        return res;
    }
}
