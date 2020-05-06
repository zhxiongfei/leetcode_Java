package 未分组;

/*
给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

        示例 1:
        给定的树 s:

        3
        / \
        4   5
        / \
        1   2
        给定的树 t：

        4
        / \
        1   2
        返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。

        示例 2:
        给定的树 s：

        3
        / \
        4   5
        / \
        1   2
        /
        0
        给定的树 t：

        4
        / \
        1   2
        返回 false。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/subtree-of-another-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _572_另一个树的子树 {

    public boolean isSubtree(TreeNode s, TreeNode t) {

        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        String s1 = getSeriesString(s,sb1);
        String s2 = getSeriesString(t,sb2);
        return s1.contains(s2);
    }

    public String getSeriesString(TreeNode node,StringBuilder sb){

        if (node == null) return sb.append("#!").toString();

        String leftV = getSeriesString(node.left,sb);
        String rightV = getSeriesString(node.right,sb);

        int val = node.val;
        String string = String.valueOf(val) + "!";
        sb.append(string);
        return sb.toString();
    }
}
