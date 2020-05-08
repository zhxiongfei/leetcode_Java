package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/*
给定一个 N 叉树，找到其最大深度。

        最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

        例如，给定一个 3叉树 :

         



         

        我们应返回其最大深度，3。



        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _559_N叉树的最大深度 {

    // 递归
    public int maxDepthRevursive(Node root) {
        if (root == null) return 0;

        int max = 1;
        for (int i = 0; i < root.children.size(); i++) {
            max = Math.max(max,maxDepthRevursive(root.children.get(i)) + 1);
        }

        return max;
    }

    // 迭代 层序遍历
    public int maxDepth(Node root) {
        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node node = root;

        int height = 0;
        while (!queue.isEmpty()){
            int size = queue.size();

            while (size > 0){
                node = queue.remove();

                for (int i = 0; i < node.children.size(); i++) {
                    queue.add(node.children.get(i));
                }

                size --;
            }

            height ++;
        }

        return height;
    }
}




