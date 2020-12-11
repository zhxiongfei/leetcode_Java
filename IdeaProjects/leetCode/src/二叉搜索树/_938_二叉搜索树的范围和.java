package 二叉搜索树;

import com.sun.management.UnixOperatingSystemMXBean;

import java.util.List;
import java.util.Stack;

/*
给定二叉搜索树的根结点 root，返回 L 和 R（含）之间的所有结点的值的和。

        二叉搜索树保证具有唯一的值。

         

        示例 1：

        输入：root = [10,5,15,3,7,null,18], L = 7, R = 15
        输出：32
        示例 2：

        输入：root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
        输出：23

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/range-sum-of-bst
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _938_二叉搜索树的范围和 {

    /**
     * 中序遍历， 递加在 [L , R] 范围内的元素即可。
     * 递归
     */
    int sum = 0;
    public int rangeSumBST2(TreeNode root, int L, int R){
        if (root == null) return 0;
        rangeSumBST2(root.left, L ,R);
        if (root.val >= L && root.val <= R) sum += root.val;
        // 加一个剪枝处理
        // 当 root.val > high 时, 就没必要往下遍历了
        if (root.val > R) return sum;

        rangeSumBST2(root.right,L,R);
        return sum;
    }

    public int rangeSumBST(TreeNode root, int L, int R){
        if(root == null) return 0;
        if(root.val < L) return rangeSumBST(root.right, L, R);
        if(root.val > R) return rangeSumBST(root.left, L, R);
        return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
    }

    /**
     * 中序遍历， 递加在 [L , R] 范围内的元素即可。
     * 迭代
     */
    public int rangeSumBST1(TreeNode root, int L, int R){
        if (root == null) return 0;
        // 保存最终结果
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val >= L && root.val <= R) sum += root.val;
            root = root.right;
        }
        return sum;
    }


    public static void main(String[] args) {

        TreeNode node = new TreeNode(10);
        node.left = new TreeNode(5);
        node.right = new TreeNode(15);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(7);

        node.right.right = new TreeNode(18);

        _938_二叉搜索树的范围和 test = new _938_二叉搜索树的范围和();
        int res = test.rangeSumBST(node,7,15);
        if (res > 0){

        }
    }
}
