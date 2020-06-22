package 链表;

/*
给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

        示例 1:

        输入: 1->2->3->4->5->NULL, k = 2
        输出: 4->5->1->2->3->NULL
        解释:
        向右旋转 1 步: 5->1->2->3->4->NULL
        向右旋转 2 步: 4->5->1->2->3->NULL
        示例 2:

        输入: 0->1->2->NULL, k = 4
        输出: 2->0->1->NULL
        解释:
        向右旋转 1 步: 2->0->1->NULL
        向右旋转 2 步: 1->2->0->NULL
        向右旋转 3 步: 0->1->2->NULL
        向右旋转 4 步: 2->0->1->NULL

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/rotate-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _61_旋转链表 {

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // 找到链表总长度
        // 同时找到链表的最后一个节点
        ListNode n = head;
        int cnt = 0;
        ListNode lastNode = null;
        while (n != null){
            lastNode = n;
            n = n.next;
            cnt ++;
        }

        // 有效旋转的数量
        k %= cnt;
        // 如果有效旋转为0， 则直接return head
        if (k == 0) return head;

        // 找到链表的 旋转的前一个节点
        ListNode slow = head;
        ListNode fast = head;
        int i = 0;
        while (fast != null){
            fast = fast.next;
            if (i ++ >= k + 1){
                slow = slow.next;
            }
        }

        // newHead 保存 新头节点
        ListNode newHead = slow.next;
        // 旋转前一个节点 置空
        slow.next = null;

        // 最后一个节点 指向头节点
        lastNode.next = head;

        return newHead;
    }


    public static void main(String[] args) {

        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);
        node.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next = new ListNode(6);

        rotateRight(node, 4);
    }

}
