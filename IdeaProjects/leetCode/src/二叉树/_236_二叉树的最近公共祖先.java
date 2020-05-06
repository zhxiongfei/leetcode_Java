package 二叉树;

/*
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

        例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

        示例 1:

        输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        输出: 3
        解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
        示例 2:

        输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        输出: 5
        解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _236_二叉树的最近公共祖先 {

    // 存放 val 对应的 父节点
    Map<Integer,TreeNode> parent;
    public void dfs(TreeNode node,TreeNode par){
        if (node == null) return;

        parent.put(node.val,par);
        dfs(node.left,node);
        dfs(node.right,node);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        // 初始化map
        parent = new HashMap<>();

        // map赋值
        dfs(root,null);

        // p的父节点数组
        ArrayList l1 = parentList(p);

        // q的父节点数组
        ArrayList l2 = parentList(q);

        // 将p的父节点数组转 set
        HashSet set = new HashSet(l1);

        // 遍历q的父结点数组 若set中包含，则为最近公共祖先。
        for (int i = 0; i < l2.size(); i++) {
            TreeNode node = (TreeNode)l2.get(i);
            if (set.contains(node)) return node;
        }

        // 若找不到 null
        return null;
    }

    // 找到结点的父节点数组
    public ArrayList parentList(TreeNode node){
        ArrayList l = new ArrayList();

        while (node != null){
            l.add(node);
            node = parent.get(node.val);
        }

        return l;
    }
}
