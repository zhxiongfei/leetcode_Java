package 探索头条;

public class 合并K个排序链表 {

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

    public ListNode mergeKLists(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l > r) return null;

        int mid = (l + r) >> 1;

        ListNode left = mergeKLists(lists, l, mid);
        ListNode right = mergeKLists(lists, mid, r);
        return mergeTwoLists(left, right);
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        return mergeKLists(lists, 0, lists.length - 1);
    }
}
