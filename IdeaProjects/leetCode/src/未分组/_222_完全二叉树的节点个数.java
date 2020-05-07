package 未分组;

/*
给出一个完全二叉树，求出该树的节点个数。

        说明：

        完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。

        示例:

        输入:
        1
        / \
        2   3
        / \  /
        4  5 6

        输出: 6

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _222_完全二叉树的节点个数 {

    public int countNodes(TreeNode root) {

        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public int countNodes1(TreeNode root) {
        if (root == null) return 0;

        int height_l = height(root.left);
        int height_r = height(root.right);

        if (height_l == height_r){
            // 左子树一定是满二叉树
            return (1 << height_l) + countNodes(root.right);
        }else {
            // 右子树一定是满二叉树
            return (1 << height_r) + countNodes(root.left);
        }
    }

    public int height(TreeNode root){
        if (root == null) return 0;

        int height = 0;
        TreeNode node = root;
        while (node != null){
            height ++;
            node = node.left;
        }

        return height;
    }
}
