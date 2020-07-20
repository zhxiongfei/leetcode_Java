package 栈;

import com.sun.source.tree.LiteralTree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。

        每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。

        返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。

        注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。

         

        示例 1：

        输入：[2,1,5]
        输出：[5,5,0]
        示例 2：

        输入：[2,7,4,3,5]
        输出：[7,0,5,5,0]
        示例 3：

        输入：[1,7,5,1,9,2,5,1]
        输出：[7,9,9,9,0,5,0,0]
         

        提示：

        对于链表中的每个节点，1 <= node.val <= 10^9
        给定列表的长度在 [0, 10000] 范围内

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/next-greater-node-in-linked-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1019_链表中的下一个更大节点 {

    /**
     *
     * 每日温度的变形
     * 单调栈 解决 next greater number 问题
     *
     * 当栈为空， 或者当前元素 < 栈顶元素时{
     *      直接入栈
     * }
     * 否则{
     *     while 循环比较, 当栈不为空，且 当前元素 > 栈顶元素时
     *           当前元素，就是比 栈顶元素大的下一个节点
     * }
     *
     *
     * */
    public static int[] nextLargerNodes(ListNode head) {

        List<Integer> list = new ArrayList<>();
        int cnt = 0;
        while (head != null){
            cnt ++;
            list.add(head.val);
            head = head.next;
        }

        int[] res = new int[cnt];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if (stack.isEmpty() || list.get(stack.peek()) > num){
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && list.get(stack.peek()) < num) {
                int idx = stack.pop();
                res[idx] = num;
            }

            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        // [2,7,4,3,5]
        ListNode node = new ListNode(2);
        node.next = new ListNode(7);
        node.next.next = new ListNode(4);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(5);

        nextLargerNodes(node);
    }
}
