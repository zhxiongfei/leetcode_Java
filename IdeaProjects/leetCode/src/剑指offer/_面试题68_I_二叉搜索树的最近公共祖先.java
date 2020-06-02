package 剑指offer;

/*
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

        百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

        例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]



         

        示例 1:

        输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        输出: 6
        解释: 节点 2 和节点 8 的最近公共祖先是 6。
        示例 2:

        输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        输出: 2
        解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
         

        说明:

        所有节点的值都是唯一的。
        p、q 为不同节点且均存在于给定的二叉搜索树中。
        注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import 二叉搜索树._235_二叉搜索树的最近公共祖先;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class _面试题68_I_二叉搜索树的最近公共祖先 {

    /*
    *
    *  dfs二叉树， 字典中保存 <节点 : 父节点>
    *  每一个节点，都可以在字典中取出其父节点
    *
    *  getParents方法，用来获取某一节点的所有父节点
    *
    *  分别获取 p 和 q 的父节点数组
    *  取两个数组第一个相交的元素 即为最近公共祖先
    *
    * */
    HashMap<TreeNode, TreeNode> map = new HashMap<>();
    public void dfs(TreeNode node, TreeNode parent){
        if (node == null) return;

        map.put(node,parent);

        dfs(node.left, node);
        dfs(node.right, node);
    }

    public List getParents(TreeNode node){

        List<TreeNode> list = new ArrayList<>();

        list.add(node);
        TreeNode parent = map.get(node);
        while (parent != null){
            map.put(node, parent);

            node = parent;
            parent = map.get(node);

            list.add(node);
        }
        return list;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 构建父节点字典
        dfs(root,null);

        // 获取 p 节点，祖先节点数组
        List<TreeNode> l1 = getParents(p);

        // 获取 q 节点，祖先节点数组
        List<TreeNode> l2 = getParents(q);

        // 找到两个数组中 第一个相交元素
        HashSet<TreeNode> set = new HashSet(l1);
        for (int i = 0; i < l2.size(); i++) {
            TreeNode node = l2.get(i);
            if (set.contains(node)) return node;
        }

        return root;
    }

    // 利用了二叉搜索树的性质
    // 二叉搜索树的性质：左子树的值 < 根节点 < 右子树的值。
    // 所以当 root的值 比 p 和 q的值都大时, 则最近公共祖先一定根节点的左边
    // 当root的值 比 p 和 q的值都小时， 则最近公共祖先一定在跟节点右边
    // 否砸，root就是最近公共祖先节点
    // 递归
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor1(root.left, p, q);
        }else if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor1(root.right, p, q);
        }
        return root;
    }

    // 迭代
    // 思路同上
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;
        while (root != null){

            if (root.val > q.val && root.val > p.val){
                root = root.left;
                continue;
            }else if (root.val < q.val && root.val < p.val){
                root = root.right;
                continue;
            }
            return root;
        }
        return null;
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(6);
        node.left = new TreeNode(2);
        node.left.left = new TreeNode(0);
        node.left.right = new TreeNode(4);
        node.left.right.left = new TreeNode(3);
        node.left.right.right = new TreeNode(5);
        node.right = new TreeNode(8);
        node.right.left = new TreeNode(7);
        node.right.right = new TreeNode(9);

        _面试题68_I_二叉搜索树的最近公共祖先 cls = new _面试题68_I_二叉搜索树的最近公共祖先();
        cls.lowestCommonAncestor(node,node.left,node.right);
    }
}
