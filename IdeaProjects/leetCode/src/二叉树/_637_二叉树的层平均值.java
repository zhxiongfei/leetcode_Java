package 二叉树;

/*
给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.

        示例 1:

        输入:
        3
        / \
        9  20
        /  \
        15   7
        输出: [3, 14.5, 11]
        解释:
        第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/average-of-levels-in-binary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _637_二叉树的层平均值 {
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> list = new ArrayList<>();
        if (root == null) return list;

        Queue <TreeNode>queue = new LinkedList();
        queue.add(root);

        while (!queue.isEmpty()){

            double sum = 0;
            int size = queue.size();
            int total = size;
            while (size > 0) {
                TreeNode node = queue.poll();

                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                size--;
            }

            list.add(sum * 1.0 / total);
        }

        return list;
    }
}
