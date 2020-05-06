package 链表;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

        示例:

        输入:
        [
          1->4->5,
          1->3->4,
          2->6
        ]
        输出: 1->1->2->3->4->4->5->6

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class _23_合并K个排序链表 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        ListNode l1 = lists[0];
        for (int i = 1; i < lists.length; i++) {
            l1 = mergeTwoLists(l1,lists[i]);
        }

        return l1;
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

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode newNode = new ListNode(-1);    // 虚拟头结点
        ListNode currentNode = newNode;

        while (true){
            // 最小链表节点的索引
            int minIdex = -1;

            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                ListNode l1 = lists[i];

                if (minIdex == -1 || lists[i].val < lists[minIdex].val){
                    minIdex = i;
                }
            }

            if (minIdex == -1) break;

            currentNode = currentNode.next = lists[minIdex];
            lists[minIdex] = lists[minIdex].next;
        }

        return newNode.next;
    }

    // 使用优先级队列
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode newNode = new ListNode(-1);    // 虚拟头结点
        ListNode currentNode = newNode;

        PriorityQueue <ListNode>queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 将所有链表的头节点放入优先级队列
        for (ListNode list : lists){
            if (list != null) queue.offer(list);
        }

        // 不断删除堆顶元素， 并且把堆顶元素的next 放入堆中
        while (!queue.isEmpty()){
            ListNode node = queue.poll();
            currentNode = currentNode.next = node;
            if (node.next != null){
                queue.offer(node.next);
            }
        }

        return newNode.next;
    }

    // 分治策略
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        int step = 1;
        while (step < lists.length){
            int nextStep = step << 1;
            for (int i = 0; i + step< lists.length; i += nextStep) {
                lists[i] = mergeTwoLists(lists[i],lists[i + step]);
            }
            step = nextStep;
        }

        return lists[0];
    }
}
