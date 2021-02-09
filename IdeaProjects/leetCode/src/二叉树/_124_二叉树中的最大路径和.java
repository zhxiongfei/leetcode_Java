package 二叉树;

/**
路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。该路径 至少包含一个 节点，且不一定经过根节点。\
        路径和 是路径中各节点值的总和。
        给你一个二叉树的根节点 root ，返回其 最大路径和 。

        示例 1:
        输入：root = [1,2,3]
        输出：6
        解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6

        示例 2:
        输入：root = [-10,9,20,null,null,15,7]
        输出：42
        解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
         

        提示：
        树中节点数目范围是 [1, 3 * 104]
        -1000 <= Node.val <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _124_二叉树中的最大路径和 {

    private int res = Integer.MIN_VALUE;
    private int max(TreeNode root){
        if (root == null) return 0;
        int left = Math.max(0,max(root.left));
        int right = Math.max(0,max(root.right));
        res = Math.max(res, left + right + root.val);

        return Math.max(left, right) + root.val;
    }

    public int maxPathSum(TreeNode root) {
        max(root);
        return res;
    }

}
