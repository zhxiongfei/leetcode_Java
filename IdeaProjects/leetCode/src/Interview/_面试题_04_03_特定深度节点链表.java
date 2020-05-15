package Interview;

/*
给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。

         

        示例：

        输入：[1,2,3,4,5,null,7,8]

        1
        /  \
        2    3
        / \    \
        4   5    7
        /
        8

        输出：[[1],[2,3],[4,5,7],[8]]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/list-of-depth-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import com.sun.source.tree.Tree;

import java.util.*;

public class _面试题_04_03_特定深度节点链表 {

    // 二叉树的层序遍历
    // 把每一层的元素串成一个链表
    // 将链表分别加入数组
    // 最终返回数组即可
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return null;

        ArrayList<ListNode> arrayList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()){
            int size = queue.size();

            ListNode listnode = new ListNode(-1);
            ListNode cur = listnode;
            while (size > 0){

                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                cur.next = new ListNode(node.val);
                cur = cur.next;
                size --;
            }

            arrayList.add(listnode.next);
        }

        return arrayList.toArray(ListNode[]::new);
    }

    public static void main(String[] args) {
        _面试题_04_03_特定深度节点链表 cls = new _面试题_04_03_特定深度节点链表();
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.right.right = new TreeNode(7);
        node.left.left.left = new TreeNode(8);
        cls.listOfDepth(node);
    }

}
