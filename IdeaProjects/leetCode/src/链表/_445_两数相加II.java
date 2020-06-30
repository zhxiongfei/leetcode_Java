package 链表;

/*
给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

        你可以假设除了数字 0 之外，这两个数字都不会以零开头。

         

        进阶：

        如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

         

        示例：

        输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        输出：7 -> 8 -> 0 -> 7

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-two-numbers-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;
import java.util.Stack;

public class _445_两数相加II {

    /**
     *
     * 反转链表
     *
     * */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode newHead = new ListNode(-1);
        ListNode current = newHead;
        int carry = 0;
        while (l1 != null || l2 != null){

            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;

            int val= v1 + v2 + carry;
            carry = val >= 10 ? 1 : 0;
            val %= 10;

            current.next = new ListNode(val);
            current = current.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry > 0){
            current.next = new ListNode(carry);
        }

        return reverse(newHead.next);
    }

    public ListNode reverse(ListNode node){
        if (node == null || node.next == null) return node;

        ListNode newNode = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return newNode;
    }

    /**
     *
     * 使用栈
     *
     * 使用栈 反转 l1 和 l2 两个链表
     *
     * 使用头插法，组装结果链表
     *
     * */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        Stack<ListNode> stack1 = new Stack<>();
        while (l1 != null){
            stack1.add(l1);
            l1 = l1.next;
        }

        Stack<ListNode> stack2 = new Stack<>();
        while (l2 != null){
            stack2.add(l2);
            l2 = l2.next;
        }

        ListNode newHead = null;
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0){
            int v1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            int v2 = stack2.isEmpty() ? 0 : stack2.pop().val;

            int sum = v1 + v2 + carry;
            carry = sum >= 10 ? 1 : 0;
            sum %= 10;
            ListNode node = new ListNode(sum);
            node.next = newHead;
            newHead = node;
        }

        return newHead;
    }

    public static void main(String[] args) {
        _445_两数相加II cls = new _445_两数相加II();

        // [7,2,4,3]
        // [5,6,4]

        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = cls.addTwoNumbers1(l1, l2);
        if (res != null){

        }
    }
}
