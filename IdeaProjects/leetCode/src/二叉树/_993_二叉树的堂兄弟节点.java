package 二叉树;

/*
在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。

        如果二叉树的两个节点深度相同，但父节点不同，则它们是一对堂兄弟节点。

        我们给出了具有唯一值的二叉树的根节点 root，以及树中两个不同节点的值 x 和 y。

        只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true。否则，返回 false。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/cousins-in-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


        思路

        当且仅当一对节点深度相同而父节点不相同时，它们是堂兄弟节点。一种非常直接的方法就是通过某种方法求出每一个节点的深度与父节点。
*/

import java.util.HashMap;
import java.util.Map;

public class _993_二叉树的堂兄弟节点 {

    Map<Integer,Integer> depth;
    Map<Integer,TreeNode> parent;

    public void dfs(TreeNode node,TreeNode par){
        if (node == null) return;
        depth.put(node.val,par != null ? 1+depth.get(par.val) : 0);
        parent.put(node.val,par);
        dfs(node.left,node);
        dfs(node.right,node);
    }

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return false;

        depth = new HashMap<>();
        parent = new HashMap<>();

        dfs(root,null);

        // 当且仅当一对结点深度相同 且 父节点不同时。 他们是堂兄弟结点。
        return (depth.get(x) == depth.get(y) && parent.get(x) != parent.get(y));
    }
}
