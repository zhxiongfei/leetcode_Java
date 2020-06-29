package 链表;

/*
给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
        将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

        你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

        示例 1:

        给定链表 1->2->3->4, 重新排列为 1->4->2->3.
        示例 2:

        给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reorder-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.List;

public class _143_重排链表 {

    /**
     *
     * 递归
     * 做递归题目时，先不用考虑如何编码
     * 先想清楚，递归函数的作用
     *
     * 此递归函数的作用是，传入 head 节点, 将 head之后的节点交换
     *
     * */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        // 找到中间节点
        ListNode middle = middle(head);

        // 反转中间节点
        ListNode reverseMid = reverse(middle);

        // 拼接 head 和 反转后的中间节点
        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        boolean flag = true;
        while (head != null && reverseMid != null){
            if (flag){
                // 拼接 head
                node.next = head;
                head = head.next;
            }else {
                // 拼接 reverseMid
                node.next = reverseMid;
                reverseMid = reverseMid.next;
            }

            node = node.next;
            flag = !flag;
        }

        head = newHead.next;
    }

    /**
     *
     * 快慢指针 查找 链表中间节点
     *
     * */
    public ListNode middle(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) slow = slow.next;

        return slow;
    }

    /**
     *
     * 反转链表
     *
     * */
    public ListNode reverse(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode newHead = null;
        ListNode node = head;
        while (node != null){
            ListNode tmp = node.next;
            node.next = newHead;
            newHead = node;
            node = tmp;
        }
        return newHead;
    }

    public static void main(String[] args) {
        _143_重排链表 cls = new _143_重排链表();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);


        cls.reorderList(head);
    }
}
