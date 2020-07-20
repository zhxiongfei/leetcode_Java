package 二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。

        例如，给定一个 3叉树 :

         



         

        返回其层序遍历:

        [
        [1],
        [3,2,4],
        [5,6]
        ]
         

        说明:

        树的深度不会超过 1000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _429_N叉树的层序遍历 {

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) return lists;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();

            int size = queue.size();
            while (size -- > 0){

                Node node = queue.remove();
                list.add(node.val);

                for (int i = 0; i < node.children.size(); i++) {
                    queue.add(node.children.get(i));
                }
            }
            lists.add(list);
        }

        return lists;
    }

}
