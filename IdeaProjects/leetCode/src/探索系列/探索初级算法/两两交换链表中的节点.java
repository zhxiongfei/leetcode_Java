package 探索系列.探索初级算法;

/*
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

        你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



        示例:

        给定 1->2->3->4, 你应该返回 2->1->4->3.
 */

public class 两两交换链表中的节点 {

    /**
     *
     * 迭代
     * 从头开始遍历链表
     *      遍历当前节点时，直接将 当前节点 与 其next节点调换位置
     * 而后继续遍历下一个节点
     * 重复以上操作
     *
     * */
    public static ListNode swapPairs1(ListNode head) {
        ListNode prev = null;
        ListNode node = head;
        while (node != null && node.next != null){
            ListNode tmp = node.next;
            node.next = node.next.next;
            tmp.next = node;

            if (prev == null){
                head = tmp;
            }else {
                prev.next = tmp;
            }

            prev = node;
            node = node.next;
        }
        return head;
    }

    /**
     *
     * 递归
     * 递归题目，有一个诀窍
     * 先不要考虑，算法的具体实现步骤
     * 先搞清楚，这个算法在解决什么问题
     *
     * 此题目中，算法解决的问题 就是 两两交换 head 节点
     * 所以 swapPairs(head.next.next), 我们就得到了 当前节点的 下下个节点反转后的链表
     *
     * 得到 next_next 之后，我们要做的就很简单了
     * 将 head 和 head.next交换
     * 交换后，再与 next_next串起来就可以咯
     *
     * 这样想的话，递归还是比较容易理解的。
     *
     * */
    public static ListNode swapPairs(ListNode head) {
        if (head == null | head.next == null) return head;

        ListNode node = head;
        // 将当前节点的 下下个节点反转
        ListNode next = swapPairs(head.next.next);

        // 反转 node 和 node.next
        // 然后与 next 拼接即可
        ListNode tmp = node.next;
        node.next = node.next.next;
        tmp.next = node;
        head = tmp;

        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        swapPairs(node);
    }

}
