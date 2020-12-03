package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

        示例:

        输入: [1,2,3,null,5,null,4]
        输出: [1, 3, 4]
        解释:

           1            <---
         /   \
        2     3         <---
         \     \
          5     4       <---

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/binary-tree-right-side-view
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _199_二叉树的右视图 {

    /**
     *
     * BFS 最直观的做法
     * 广度优先搜索 -> 记录每层最后一个元素
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();
            int val = 0;
            while (size -- > 0){
                TreeNode node = queue.poll();
                val = node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(val);
        }
        return list;
    }

    /**
     *
     * DFS
     * 深度优先搜索
     * 如果保证 根节点 -> 右子节点 -> 左子节点的顺序访问
     * 那么每一层第一次访问的节点， 就是最右边的节点.
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     *
     * https://leetcode-cn.com/problems/binary-tree-right-side-view/solution/er-cha-shu-de-you-shi-tu-by-leetcode-solution/
     *
     * */
    List<Integer> res = new ArrayList<>();
    public void dfs(TreeNode root, int depth){
        if (root == null) return;

        // 如果当前节点所在深度还没有出现在res里
        // 说明在该深度下当前节点是第一个被访问的节点
        // 将其加入 res 中
        if (depth == res.size()) res.add(root.val);
        depth ++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }

    public List<Integer> rightSideView1(TreeNode root) {
        dfs(root, 0);
        return res;
    }
}
