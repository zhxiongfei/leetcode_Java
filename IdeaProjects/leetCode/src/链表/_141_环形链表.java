
/*
*
* 给定一个链表，判断链表中是否有环。
* 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
* 链接：https://leetcode-cn.com/problems/linked-list-cycle
* */

package 链表;

import java.util.HashSet;

public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    public boolean hasCycle1(ListNode head) {

        if (head == null || head.next == null) return false;

        HashSet<ListNode> set = new HashSet<>();

        while (head != null){

            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
