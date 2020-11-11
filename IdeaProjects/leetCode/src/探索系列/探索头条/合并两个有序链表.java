package 探索系列.探索头条;

import java.util.List;

public class 合并两个有序链表 {

    /**
     * 迭代
     * */
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        while (l1 != null || l2 != null){
            if (l1 == null){
                node.next = l2;
                break;
            }
            if (l2 == null){
                node.next = l1;
                break;
            }
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 < v2){
                node.next = l1;
                l1 = l1.next;
            }else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        return newHead.next;
    }

    /**
     * 递归
     * */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val){
            ListNode newNode = new ListNode(l1.val);
            newNode.next = mergeTwoLists(l1.next, l2);
            return newNode;
        }
        ListNode newNode = new ListNode(l2.val);
        newNode.next = mergeTwoLists(l1, l2.next);
        return newNode;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(2);
        n1.next.next = new ListNode(4);

        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);

        ListNode node = mergeTwoLists1(n1, n2);

    }

}
