package 二叉搜索树;

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

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.sun.source.tree.Tree;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.*;

public class _235_二叉搜索树的最近公共祖先 {

    // 保存 <值 ， 父节点>
    HashMap<Integer, TreeNode> hashMap;

    public void dfs(TreeNode node ,TreeNode parent){
        if (node == null) return;

        hashMap.put(node.val,parent);

        dfs(node.left,node);
        dfs(node.right,node);
    }

    public List getParents(TreeNode node){

        List<TreeNode> plist = new LinkedList<>();

        TreeNode pnode = node;
        while (pnode != null){
            plist.add(pnode);
            pnode = hashMap.get(pnode.val);
        }

        return plist;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        hashMap = new HashMap<>();

        // 处理 父节点 字典
        dfs(root,null);

        // p 的所有父节点
        List<TreeNode> plist = getParents(p);

        // q 的所有父节点
        List<TreeNode> qlist = getParents(q);

        HashSet set = new HashSet(plist);

        for (int i = 0; i < qlist.size(); i++) {
            if (set.contains(qlist.get(i)))
                return qlist.get(i);
        }

        return null;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        if (root.val > q.val && root.val > p.val){

            return lowestCommonAncestor2(root.left,p,q);
        }else if (root.val < q.val && root.val < p.val){
            return lowestCommonAncestor2(root.right,p,q);
        }

        return root;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        while (root != null) {
            if (root.val > q.val && root.val > p.val) {
                root = root.left;
            } else if (root.val < q.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
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

        _235_二叉搜索树的最近公共祖先 cls = new _235_二叉搜索树的最近公共祖先();
        cls.lowestCommonAncestor(node,node.left,node.right);
    }

}
