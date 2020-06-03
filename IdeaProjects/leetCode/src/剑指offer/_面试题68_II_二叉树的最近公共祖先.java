package 剑指offer;

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
         

        说明:

        所有节点的值都是唯一的。
        p、q 为不同节点且均存在于给定的二叉树中。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;

public class _面试题68_II_二叉树的最近公共祖先 {

    /*
    *
    * 前两天做过一道二叉搜索树的公共祖先
    * 方法一
    *   完全没有利用到二叉搜索的性质，其实就是二叉树的最近公共祖先
    *   1， 用额外的存储数据结构字典，存储<节点 ： 父节点>数组
    *   2， 写一个方法，传入某节点，找到其所有祖先节点，并保存到list
    *   3， 找两个list的最近的相同元素，即是最近公共祖先
    *
    * */

    // 获取存储所有节点父节点的字典
    HashMap <TreeNode,TreeNode> map = new HashMap<>();
    public void getAnsestorMap(TreeNode root, TreeNode parent){
        if (root == null) return;
        map.put(root,parent);
        getAnsestorMap(root.left, root);
        getAnsestorMap(root.right, root);
    }

    public List<TreeNode> getAnsestorList(TreeNode node){
        List<TreeNode> list = new ArrayList<>();
        if (node == null) return list;

        TreeNode pnode = node;
        while (pnode != null){
            list.add(pnode);
            pnode = map.get(pnode);
        }

        return list;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {

        getAnsestorMap(root,null);

        List <TreeNode>l1 = getAnsestorList(p);
        List <TreeNode>l2 = getAnsestorList(q);

        Set<TreeNode> set = new HashSet<>(l1);

        for (int i = 0; i < l2.size(); i++) {
            if (set.contains(l2.get(i))) return l2.get(i);
        }

        return null;
    }

    /*
    *
    * 思路二： 递归
    *   首先递归函数的作用是，找到 p 和 q元素在 root树中的公共祖先
    *
    *   1，如果 root p q三者中一个为空，则return null
    *   2，如果 root == p 或者 root == q. 则结果一定是root
    *   3，然后递归左右子树，因为是递归，我们可以认为，左右子树已经计算出结果 left 和 right
    *   4，当left为空时，只能在右子树中找
    *   5，当right为空时，只能在 左子树中找
    *   6，否则都不为空时，说明分别在 p 和 q左右子树中。root为最近公共祖先
    * */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        if (root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;

        return root;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);

        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(9);

        _面试题68_II_二叉树的最近公共祖先 cls = new _面试题68_II_二叉树的最近公共祖先();
        cls.lowestCommonAncestor1(node, node.left.left, node.left.right);
    }
}
