package 二叉搜索树;

import java.util.ArrayList;
import java.util.List;

/**
给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

        案例 1:

        输入:
        5
        / \
        3   6
        / \   \
        2   4   7

        Target = 9

        输出: True
         

        案例 2:

        输入:
        5
        / \
        3   6
        / \   \
        2   4   7

        Target = 28

        输出: False

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _653_两数之和_IV_输入_BST {

    List<Integer> list = new ArrayList<>();
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

    public boolean findTarget(TreeNode root, int k) {
        dfs(root);
        int i = 0, j = list.size() - 1;
        while (i < j){
            int sum = list.get(i) + list.get(j);
            if (sum == k) return true;
            if (sum > k) j --;
            else i ++;
        }
        return false;
    }

    public static void main(String[] args) {
        _653_两数之和_IV_输入_BST cls = new _653_两数之和_IV_输入_BST();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        cls.findTarget(root, 5);
    }

}
