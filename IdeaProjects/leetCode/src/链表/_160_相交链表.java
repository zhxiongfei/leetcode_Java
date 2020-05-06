package 链表;

/*
编写一个程序，找到两个单链表相交的起始节点。

        如下面的两个链表：



        在节点 c1 开始相交。

         

        示例 1：



        输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
        输出：Reference of the node with value = 8
        输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
         

        示例 2：



        输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
        输出：Reference of the node with value = 2
        输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
         

        示例 3：



        输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
        输出：null
        输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
        解释：这两个链表不相交，因此返回 null。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import com.sun.source.tree.BinaryTree;

import java.util.HashMap;

public class _160_相交链表 {

    /*
    * 1,最暴力，双重遍历
    * 2,hashmap存储，headA的结点。 遍历B，若存在遍历的为相交结点。
    * 3,遍历A和B，当a遍历完时，把a指向B的头节点。 当b遍历完时，把b指向A的头节点。当a和b相撞时，就是相交结点
    * */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        ListNode curA = headA;
        ListNode curB = headB;

        HashMap map = new HashMap();
        while (curA != null){

            map.put(curA,curA.val);
            curA = curA.next;
        }

        while (curB != null){

            if (map.get(curB) != null) return curB;
            curB = curB.next;
        }

        return null;
    }

    public static ListNode getIntersectionNode3(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) return null;

        ListNode curA = headA;
        ListNode curB = headB;

        while (curA != null || curB != null){

            if (curA == null) curA = headB;
            if (curB == null) curB = headA;

            if (curA == curB) return curA;

            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }

    public static void main(String[] args){

        // listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]

        ListNode headA = new ListNode(4);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(8);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(5);

        ListNode headB = new ListNode(5);
        ListNode b1 = new ListNode(0);

        headA.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;

        headB.next = b1;
        b1.next = a1;


        ListNode node = getIntersectionNode3(headA,headB);
        System.out.println(node);
    }
}
