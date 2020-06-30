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

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class _143_重排链表 {

    /**
     *
     * 解法一： 存储
     * 由于链表 不能随机访问
     * 所以，我们遍历链表， 把链表中的节点都放入 数组 实现随机访问
     *
     * 放入数组后，用头尾双指针拼接节点
     *
     * */
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;

        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (head != null){
            listNodes.add(head);
            head = head.next;
        }

        int i = 0, j = listNodes.size() - 1;
        while (i < j){
            listNodes.get(i).next = listNodes.get(j);
            i ++;

            // 奇数个节点，会提前相遇
            if (i == j) break;

            listNodes.get(j).next = listNodes.get(i);
            j --;
        }

        listNodes.get(i).next = null;
    }

    /**
     *
     * 双端队列
     * 其实跟 数组差不多
     * 操作更方便一些
     *
     * */
    public void reorderList3(ListNode head) {
        Deque<ListNode> queue = new LinkedList<>();
        ListNode cur = head;
        while (cur != null) {
            queue.addLast(cur);
            cur = cur.next;
        }
        while (!queue.isEmpty()) {
            if (cur == null) {
                cur = queue.pollFirst();
            } else {
                cur.next = queue.pollFirst();
                cur = cur.next;
            }
            cur.next = queue.pollLast();
            cur = cur.next;
        }
        if (cur != null) {
            cur.next = null;
        }
    }

    /**
     *
     * 解法二: 递归
     *
     *
     * */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        int len = 0;
        ListNode node = head;
        while (node != null){
            len ++;
            node = node.next;
        }

        reorderListHelper(head, len);
    }

    /**
     *
     * 递归函数的作用是， 传入 head 取出尾元素
     *
     * */
    public ListNode reorderListHelper(ListNode head, int len){
        if (len == 1){
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }

        if (len == 2){
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }

        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }

    /**
     *
     * 解法三 : 快慢指针找中间，反转后半段，再拼接
     *
     * 先快慢指针找到链表中间节点
     * 再反转后半部分链表
     *
     * 再拼接前半部分 和 反转后的后半部分
     *
     * */
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null) return;

        // 找到中间节点
        ListNode middle = middle(head);

        // 反转中间节点
        ListNode lastnode = reverseRecursive(middle);

        // 拼接 head 和 反转后的中间节点
        ListNode prevnode = head;
        while (prevnode != null && lastnode != null) {

            ListNode prev = prevnode.next;
            ListNode last = lastnode.next;

            prevnode.next = lastnode;
            lastnode.next = prev;

            prevnode = prev;
            lastnode = last;
        }

        if (prevnode != null) prevnode.next = lastnode;
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
     * 递归
     *
     * */
    public ListNode reverseRecursive(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode node = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return node;
    }

    /**
     *
     * 反转链表
     * 迭代
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

        cls.reorderList3(head);
    }
}
