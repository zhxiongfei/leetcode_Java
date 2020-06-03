package 剑指offer;

/*
从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

         

        例如:
        给定二叉树: [3,9,20,null,null,15,7],

        3
        / \
        9  20
        /  \
        15   7
        返回其层次遍历结果：

        [
        [3],
        [9,20],
        [15,7]
        ]
         

        提示：

        节点总数 <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _面试题32_II_从上到下打印二叉树II {

    /*
    *
    * 这道题考的，其实就是二叉树的层序遍历
    * 利用队列来实现层序遍历
    *
    * 初始将 root 加入队列
    * 当队列不为空时，循环
    *
    * 取出对头元素，将对头元素的左右节点入队
    *
    * */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList list = new ArrayList();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> l = new ArrayList<>();
            while (size-- > 0){
                TreeNode node = queue.remove();
                l.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            list.add(l);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(4);
        node.left = new TreeNode(2);
        node.right = new TreeNode(7);

        node.left.left = new TreeNode(1);
        node.left.right = new TreeNode(3);

        node.right.left = new TreeNode(6);
        node.right.right = new TreeNode(9);

        levelOrder(node);
    }
}
