package 链表;

/*
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

        说明:
        1 ≤ m ≤ n ≤ 链表长度。

        示例:

        输入: 1->2->3->4->5->NULL, m = 2, n = 4
        输出: 1->4->3->2->5->NULL

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import javax.swing.*;
import java.util.List;

// 未解决
public class _92_反转链表II {

    public static ListNode reverseBetween(ListNode head, int m, int n) {

        ListNode prev = null;
        ListNode cur = head;

        while (m > 1){
            prev = cur;
            cur = cur.next;
            m --;
            n --;
        }

        ListNode con = prev, tail = cur;

        ListNode third = null;
        while (n > 0){
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;

            n --;
        }

        if (con != null){
            con.next = prev;
        }else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }

    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        reverseBetween(node, 2, 4);
    }
}
