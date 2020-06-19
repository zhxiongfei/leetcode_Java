package 探索头条;

import java.util.List;

public class 排序链表 {

    // 归并排序 分治策略
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 快慢指针找中心节点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;

        // 对链表的左右 分别排序
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        // 合并 两个有序链表
        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        while (left != null || right != null){
            if (left == null){
                node.next = right;
                break;
            }

            if (right == null){
                node.next = left;
                break;
            }

            int v1 = left.val;
            int v2 = right.val;
            if (v1 < v2){
                node.next = left;
                left = left.next;
            }else {
                node.next = right;
                right = right.next;
            }
            node = node.next;
        }
        return newHead.next;
    }

}
