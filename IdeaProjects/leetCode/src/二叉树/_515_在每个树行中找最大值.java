package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
您需要在二叉树的每一行中找到最大的值。

        示例：

        输入:

        1
        / \
        3   2
        / \   \
        5   3   9

        输出: [1, 3, 9]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _515_在每个树行中找最大值 {

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();

            int max = Integer.MIN_VALUE;
            while (size -- > 0){
                TreeNode node = queue.poll();

                max = Math.max(max, node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(max);
        }
        return res;
    }



}
