package DFS;

import com.sun.source.tree.Tree;

import java.util.HashMap;

/**
给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。

        回想一下：

        叶节点 是二叉树中没有子节点的节点
        树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
        如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
         

        示例 1：

        输入：root = [1,2,3]
        输出：[1,2,3]
        解释：
        最深的叶子是值为 2 和 3 的节点。
        这些叶子的最近共同祖先是值为 1 的节点。
        返回的答案为序列化的 TreeNode 对象（不是数组）"[1,2,3]" 。
        示例 2：

        输入：root = [1,2,3,4]
        输出：[4]
        示例 3：

        输入：root = [1,2,3,4,5]
        输出：[2,4,5]
         

        提示：

        给你的树中将有 1 到 1000 个节点。
        树中每个节点的值都在 1 到 1000 之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1123_最深叶节点的最近公共祖先 {

    // 如果当前节点是最深叶子节点的最近公共祖先，那么它的左右子树的高度一定是相等的
    // 否则高度低的那个子树的叶子节点深度一定比另一个子树的叶子节点的深度小
    // 所以向高度较高的子数中查找
    // 所以只需要dfs遍历找到左右子树高度相等的根节点即出答案

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if (root == null) return null;

        int height_l = depth(root.left);
        int height_r = depth(root.right);

        if (height_l == height_r) return root;

        if (height_l > height_r) return lcaDeepestLeaves(root.left);

        return lcaDeepestLeaves(root.right);
    }

    // 求节点高度
    public int depth(TreeNode node){
        if (node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);

        _1123_最深叶节点的最近公共祖先 cls = new _1123_最深叶节点的最近公共祖先();
        TreeNode res = cls.lcaDeepestLeaves(node);

        if (res == null){

        }
    }

}
