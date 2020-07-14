package DFS;

import 链表.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
请考虑一颗二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。



        举个例子，如上图所示，给定一颗叶值序列为 (6, 7, 4, 9, 8) 的树。

        如果有两颗二叉树的叶值序列是相同，那么我们就认为它们是 叶相似 的。

        如果给定的两个头结点分别为 root1 和 root2 的树是叶相似的，则返回 true；否则返回 false 。

         

        提示：

        给定的两颗树可能会有 1 到 200 个结点。
        给定的两颗树上的值介于 0 到 200 之间。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/leaf-similar-trees
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _872_叶子相似的树 {

    /**
     *
     * dfs 二叉树
     * 分别dfs两棵树
     * 当叶子节点时, 将叶子节点加入 list
     *
     * 遍历判断 两个list是否相同
     *
     * */
    public void dfs(TreeNode node, List<Integer> list){
        if (node == null) return;

        if (node.left == null && node.right == null){
            // 叶子节点
            list.add(node.val);
        }
        dfs(node.left,list);
        dfs(node.right,list);
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> list1 = new ArrayList<>();
        dfs(root1, list1);

        List<Integer> list2 = new ArrayList<>();
        dfs(root2,list2);

        if (list1.size() != list2.size()) return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i)) return false;
        }
        return true;
    }

}
