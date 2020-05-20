package Interview;

/*
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

        示例1：

        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4
        限制：

        0 <= 链表长度 <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题25_合并两个排序的链表 {

    // 思路一
    // 迭代
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {

        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                node.next = l2;
                break;
            }

            if (l2 == null) {
                node.next = l1;
                break;
            }

            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            } else {
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }

        return newHead.next;
    }

    // 思路二
    // 递归
    // 1.当链表1为空链表时，直接返回链表2；当链表2为空链表时，返回链表1。这两种情况都是递归的边界。
    // 2.当链表1和链表2均不为空时，找出两个链表头节点中值较小者作为新链表的头节点，对于剩余的部分使用递归求解。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

}