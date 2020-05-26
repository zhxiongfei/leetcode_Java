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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<Integer> inorderTraversal(TreeNode root, List list){
        if (root == null) return list;

        inorderTraversal(root.left,list);
        list.add(root.val);
        inorderTraversal(root.right, list);
        return list;
    }


    public List<Integer> merge(List l1, List l2){
        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0, r = 0;
        while (i < l1.size() || j < l2.size()){
            int v1 = i < l1.size () ? (int) l1.get(i) : Integer.MIN_VALUE;
            int v2 = j < l2.size () ? (int)l2.get(j) : Integer.MAX_VALUE;

            if (v1 < v2){
                result.set(r ++, v1);
                i ++;
            }else {
                result.set(r ++, v2);
                j ++;
            }
        }

        return result;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        l1 = inorderTraversal(root1,l1);
        l2 = inorderTraversal(root2,l2);

        return merge(l1,l2);
    }
}
