package 链表;

/*
*
    反转一个单链表。

    示例:

    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
    进阶:
    你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/reverse-linked-list
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
* */

import java.util.List;

public class _206_反转链表 {

    /*
     * 非递归
     *
     * tmp = head.next;
     * head.next -> newHead
     * newhead -> head
     * head -> tmp
     *
     * */
    // 1 2 3 4 5

    public ListNode reverseList1(ListNode head) {

        ListNode newHead = null;

        while (head != null){
            ListNode tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }

        return newHead;
    }

    /*
     * 非递归
     * */
    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;

        while(cur != null){

            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }

    /*
    * 递归
    * */
    public ListNode reverseListRecursive(ListNode head){

        if (head == null || head.next == null) return head;

        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
