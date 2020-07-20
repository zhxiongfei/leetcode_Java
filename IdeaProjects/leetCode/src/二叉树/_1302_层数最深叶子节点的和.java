package 二叉树;

import java.security.cert.CertificateNotYetValidException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
给你一棵二叉树，请你返回层数最深的叶子节点的和。

         

        示例：



        输入：root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
        输出：15
         

        提示：

        树中节点数目在 1 到 10^4 之间。
        每个节点的值在 1 到 100 之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/deepest-leaves-sum
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1302_层数最深叶子节点的和 {

    /**
     *
     * 两遍 dfs
     * 第一遍，求出树高度
     * 第二遍遍历，是叶子节点 且 高度等于总高时，把结果加进去
     *
     * */
    public int dfs1(TreeNode root){
        if (root == null) return 0;
        return Math.max(dfs1(root.left), dfs1(root.right)) + 1;
    }

    int res = 0;
    public int dfs1(TreeNode root, int depth, int totalDepth){
        if (root == null) return 0;

        if (root.left == null && root.right == null && depth == totalDepth){
            res += root.val;
        }
        return dfs1(root.left, depth + 1, totalDepth) + dfs1(root.right, depth + 1, totalDepth);
    }

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.left == null) return root.val;

        int height = dfs1(root);
        dfs1(root, 1, height);
        return res;
    }

    /**
     *
     * 一遍 dfs
     *
     * 记录两个变量
     * maxDepth 代表二叉树最大深度
     * sum 代表最终结果，即最后一层的和
     *
     * dfs 二叉树
     * depth < maxDepth 时不做任何处理
     * depth = maxDepth 时 sum += depth
     * depth > maxDepth 时 更新 maxDepth,且之前记录的 sum 无效。 将sum 置为 root.val
     *
     * 再分别 dfs 左右子树
     *
     * */
    int maxDepth = 0;
    int sum = 0;
    public void dfs(TreeNode root, int depth){
        if (root == null) return;

        if (depth == maxDepth){
            sum += depth;
        }else if (depth > maxDepth){
            maxDepth = depth;
            sum = root.val;
        }

        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }

    public int deepestLeavesSum1(TreeNode root) {
        dfs(root, 0);
        return sum;
    }

    /**
     *
     * 层序遍历
     *
     * 逐步记录每一层的和,最后一层的和 就是最终结果
     *
     * */
    public int deepestLeavesSum2(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()){

            res = 0;
            int size = queue.size();
            while (size -- > 0){
                TreeNode node = queue.poll();

                res += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _1302_层数最深叶子节点的和 cls = new _1302_层数最深叶子节点的和();

        TreeNode node = new TreeNode(50);
        node.right = new TreeNode(54);
        node.right.left = new TreeNode(98);
        node.right.right = new TreeNode(6);
        node.right.right.right = new TreeNode(34);

        int res = cls.deepestLeavesSum1(node);
        System.out.println(res);
    }

}
