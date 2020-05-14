package Interview;

/*
实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。

        注意：本题相对原题稍作改动

        示例：

        输入： 1->2->3->4->5 和 k = 2
        输出： 4
        说明：

        给定的 k 保证是有效的。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_02_02_返回倒数第k个节点 {

    // 此题与 题目 19 基本一样
    // 快慢指针思想
    // 快指针走k步时，慢指针开始走动
    // 快指针走到null， 慢指针指向第k个节点
    // 接下来把慢指针的val返回即可

    public int kthToLast(ListNode head , int k){

        if (head.next == null) return head.val;

        ListNode fast = head;
        ListNode slow = head;

        int step = 0;
        while (fast != null){

            if (step >= k) slow = slow.next;
            fast = fast.next;
            step ++;
        }

        return slow.val;
    }
}
