package 二叉搜索树;

/*
给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

        说明：
        你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

        示例 1:

        输入: root = [3,1,4,null,2], k = 1
        3
        / \
        1   4
        \
           2
        输出: 1
        示例 2:

        输入: root = [5,3,6,2,4,null,null,1], k = 3
        5
        / \
        3   6
        / \
        2   4
        /
        1
        输出: 3
        进阶：
        如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.sun.source.tree.LiteralTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _230_二叉搜索树中第K小的元素 {

    // 递归 中序遍历
    List<Integer> list = new ArrayList<>();
    public void dfs (TreeNode root){
        if (root == null) return;

        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    // 迭代 中序遍历
    public void dfs1(TreeNode root) {

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
    }

    // 优化遍历次数 且不需要额外数组存储
    // 递归
    int count = 1;
    int res;
    public void dfs(TreeNode root,int k){
        if (root == null) return;

        dfs(root.left, k);

        count ++;
        if (k == count) {
            res = root.val;
        }

        dfs(root.right, k);
    }

    // 迭代
    public int dfs1(TreeNode root, int k){
        Stack<TreeNode> stack = new Stack<>();

        int count = 0;
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            count ++;
            if (count == k) return root.val;

            root = root.right;
        }

        return -1;
    }


    // 题目主干
    public int kthSmallest(TreeNode root, int k) {
        dfs(root,k);
        return res;
    }

    // 测试
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(1);
        node.left.right = new TreeNode(2);
        node.right = new TreeNode(4);

        _230_二叉搜索树中第K小的元素 cls = new _230_二叉搜索树中第K小的元素();
        cls.kthSmallest(node,3);
    }
}
