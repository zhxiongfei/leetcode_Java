package 链表;

import java.util.PriorityQueue;

/*
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

        示例 1:

        输入: 4->2->1->3
        输出: 1->2->3->4
        示例 2:

        输入: -1->5->3->4->0
        输出: -1->0->3->4->5

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sort-list
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/
public class _148_排序链表 {

    // 优先级队列
    public ListNode sortList1(ListNode head) {

        if (head == null || head.next == null) return head;

        PriorityQueue<Integer> queue = new PriorityQueue();
        while (head != null){
            queue.add(head.val);

            head = head.next;
        }

        ListNode newHead =  new ListNode(-1);
        ListNode cur = newHead;
        while (!queue.isEmpty()){
            ListNode node = new ListNode(queue.poll());
            cur.next = node;
            cur = cur.next;
        }

        return newHead.next;
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        if (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode tmp = slow.next;
        slow.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(tmp);

        ListNode newNode = new ListNode(-1);    // 虚拟头结点
        ListNode currentNode = newNode;
        while (left != null || right != null){

            if (left == null){
                currentNode.next = right;
                break;
            }

            if (right == null){
                currentNode.next = left;
                break;
            }

            if (left.val <= right.val){
                currentNode.next = left;
                left = left.next;
            }else{
                currentNode.next = right;
                right = right.next;
            }
            currentNode = currentNode.next;
        }

        return newNode.next;
    }

    public static void main(String[] args){
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);

        n1.next = n2;n2.next = n3;n3.next = n4;

        ListNode res = sortList(n1);
        if (res != null){

        }
    }
}
