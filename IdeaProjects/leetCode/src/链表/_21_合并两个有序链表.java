package 链表;

/*
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

        示例：

        输入：1->2->4, 1->3->4
        输出：1->1->2->3->4->4

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.List;

public class _21_合并两个有序链表 {

    // 输入：1->2->4, 1->3->4
    // 输出：1->1->2->3->4->4

    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val){
            l1.next = mergeTwoListsRecursive(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoListsRecursive(l1,l2.next);
            return l2;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode newNode = new ListNode(-1);    // 虚拟头结点

        ListNode currentNode = newNode;
        while (l1 != null || l2 != null){

            if (l1 == null){
                currentNode.next = l2;
                break;
            }

            if (l2 == null){
                currentNode.next = l1;
                break;
            }

            if (l1.val <= l2.val){
                currentNode.next = l1;
                l1 = l1.next;
            }else{
                currentNode.next = l2;
                l2 = l2.next;
            }
            currentNode = currentNode.next;
        }

        return newNode.next;
    }
}
