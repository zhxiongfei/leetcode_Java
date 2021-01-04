package 剑指offer;

/*
给定一棵二叉搜索树，请找出其中第k大的节点。

         

        示例 1:

        输入: root = [3,1,4,null,2], k = 1
        3
        / \
        1   4
        \
           2
        输出: 4
        示例 2:

        输入: root = [5,3,6,2,4,null,null,1], k = 3
        5
        / \
        3   6
        / \
        2   4
        /
        1
        输出: 4
         

        限制：

        1 ≤ k ≤ 二叉搜索树元素个数



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _面试题54_二叉搜索树的第k大节点 {

    // 二叉树中序遍历
    // 递归
    List<Integer> list = new ArrayList<>();
    public List<Integer>dfs(TreeNode root){
        if (root == null) return list;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);

        return list;
    }

    // 迭代
    public List<Integer>inorderTraversal(TreeNode root){
        if (root == null) return list;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }

    public int kthLargest(TreeNode root, int k) {

        inorderTraversal(root);
        return list.get(list.size() - k);
    }

    /**
     * 提前剪枝
     * */
    int res, k;
    private void dfs1(TreeNode root){
        if (root == null) return;
        dfs1(root.right);

        if (-- k == 0) {
            res = root.val;
            return;
        }
        dfs1(root.left);
    }
    public int kthLargest1(TreeNode root, int k) {
        this.k = k;
        dfs1(root);
        return res;
    }
}
