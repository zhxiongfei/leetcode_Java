package 剑指offer;

/*
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

         

        示例：

        给定一个链表: 1->2->3->4->5, 和 k = 2.

        返回链表 4->5.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 */

public class _面试题22_链表中倒数第k个节点 {

    /**
     * 两边遍历
     * 第一遍 : 记录下总节点数，计算出倒数第 k 个是正数第 target 个
     * 第二遍 : 取出正数第 target 个返回
     * */
    public ListNode getKthFromEnd1(ListNode head, int k) {
        int size = 0;
        ListNode node = head;
        while (node != null){
            size ++;
            node = node.next;
        }
        int target = size - k;

        int idx = 0;
        node = head;
        while (idx < target){
            node = node.next;
            idx ++;
        }
        return node;
    }

    /**
     * 快慢指针
     * 快指针走 k 步后，慢指针开始走
     * 当快指针走向末尾时，慢指针则指向倒数第 k 个
     * */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode slow = head, fast = head;
        int step = 0;
        while (fast != null){
            if (step >= k) slow = slow.next;
            fast = fast.next;
            step ++;
        }
        return slow;
    }

}
