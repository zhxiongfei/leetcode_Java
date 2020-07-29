package 二叉树;

/**
给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一个。

        给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。

        示例 1:

        输入:
        2
        / \
        2   5
        / \
        5   7

        输出: 5
        说明: 最小的值是 2 ，第二小的值是 5 。
        示例 2:

        输入:
        2
        / \
        2   2

        输出: -1
        说明: 最小的值是 2, 但是不存在第二小的值。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/second-minimum-node-in-a-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _671_二叉树中第二小的节点 {

    /**
    让 min1 = root.val
    min1 = root.val。

    当遍历结点 node，
     如果node.val > min1 我们知道在node处的子树中的所有值都至少是node.val,

     因此在该子树中不此存在第二个最小值。因此，我们不需要搜索这个子树。
    此外，由于我们只关心第二个最小值 ans
    */

    public int helper(TreeNode root, int min){
        if (root == null) return -1;
        if (root.val > min) return root.val;

        int l = helper(root.left, min);
        int r = helper(root.right, min);

        if (l == -1) return r;
        if (r == -1) return l;
        return Math.min(l, r);
    }

    public int findSecondMinimumValue(TreeNode root) {

        return helper(root, root.val);
    }

}
