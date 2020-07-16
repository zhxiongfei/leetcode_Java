package 链表;

import java.util.HashSet;
import java.util.List;

/**
给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

        示例 1:

        输入: 1->2->3->3->4->4->5
        输出: 1->2->5
        示例 2:

        输入: 1->1->1->2->3
        输出: 2->3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _82_删除排序链表中的重复元素II {

    // 1 1 2 2 3 4 4 5
    /**
     *
     * 思路
     *
     * 使用 虚拟头节点 newHead , 以及 prev用来构造 newHead
     *
     * current 指针用来遍历链表
     * 当 current.next 不为空时，做以下循环
     *      比较 current.val 和 current.next.val 一样时 或者 set中包含 current.val时
     *      则 重复, 将元素加入 set
     *
     *      当 不相等 且 set中不包含 current.val时
     *      则 不重复, new一个 listNode 拼接 prev
     *
     * 遍历完毕, 如果 set 中不包含 current 的值,再把 current 拼接上
     *
     *
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     *
     * */
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;

        HashSet<Integer> set = new HashSet<>();
        ListNode newHead = new ListNode(-1);
        ListNode prev = newHead;
        ListNode current = head;

        while (current.next != null){
            if (current.val == current.next.val || set.contains(current.val)){
                set.add(current.val);
            }else {
                prev.next = new ListNode(current.val);
                prev = prev.next;
            }
            current = current.next;
        }
        if (!set.contains(current.val)){
            prev.next = current;
        }
        return newHead.next;
    }

    /**
     *
     * 递归
     *
     * 函数的作用是, 传入一个节点, 删除节点的重复节点
     * */
    public static ListNode deleteDuplicates(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode newHead = new ListNode(-1);
        if (head.val == head.next.val) {
            //去重
            ListNode next = head.next;
            while (next != null && next.val == head.val)
                next = next.next;
            newHead.next = deleteDuplicates(next);
        }

        else {
            newHead.next = head;
            head.next = deleteDuplicates(head.next);
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(2);
        node.next.next.next.next = new ListNode(3);
        node.next.next.next.next.next = new ListNode(3);
        node.next.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next .next.next = new ListNode(4);
        node.next.next.next.next.next .next.next.next = new ListNode(5);


        ListNode resNode = deleteDuplicates(node);
        if (resNode == null){

        }
    }

}
