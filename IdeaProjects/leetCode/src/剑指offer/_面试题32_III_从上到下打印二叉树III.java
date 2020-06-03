package 剑指offer;

/*
请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

         

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
        [20,9],
        [15,7]
        ]
         

        提示：

        节点总数 <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.lang.reflect.Array;
import java.util.*;

public class _面试题32_III_从上到下打印二叉树III {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList list = new ArrayList();
        if (root == null) return list;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> l = new ArrayList<>();
            while (size-- > 0){
                TreeNode node = queue.remove();
                l.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (flag == true) l = reverse(l);
            list.add(l);
            flag = !flag;
        }
        return list;
    }

    public static ArrayList<Integer> reverse(List<Integer> list){
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            l.add(list.get(i));
        }
        return l;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);

        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);

        levelOrder(node);
    }

}
