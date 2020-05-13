package Interview;

/*
编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。

        示例1:

        输入：[1, 2, 3, 3, 2, 1]
        输出：[1, 2, 3]
        示例2:

        输入：[1, 1, 1, 1, 2]
        输出：[1, 2]
        提示：

        链表长度在[0, 20000]范围内。
        链表元素在[0, 20000]范围内。
        进阶：

        如果不得使用临时缓冲区，该怎么解决？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;

public class _面试题_02_01_移除重复节点 {

    // 思路一
    // 用set存储，已经遍历过的元素
    // 遍历节点，set中包含时，则删除节点
    // set中不包含时，将节点值加入set
    // 时间复杂度O(N)
    // 空间复杂度O(N)
    // 空间换时间
    HashSet<Integer> set = new HashSet<>();
    public ListNode removeDuplicateNodes(ListNode head) {

        ListNode prev = null;
        ListNode node = head;
        while (node != null){
            if (set.contains(node.val)){
                // 删除node
                prev.next = node.next;
            }else {
                set.add(node.val);
                prev = node;
            }
            node = node.next;
        }

        return head;
    }

    public _面试题_02_01_移除重复节点() {
        super();
    }

    // 思路二：
    // 不使用额外的存储空间
    // 两遍循环
    // [1, 2, 3, 3, 2, 1]
    // 时间复杂度 : O(N ^ 2)
    // 空间复杂度 : O(1)
    public ListNode removeDuplicateNodes1(ListNode head){

        if (head == null) return null;

        ListNode node = head;
        while (node != null){
            ListNode cur = node;
            while (cur.next != null) {
                if (node.val == cur.next.val) {
                    cur.next = cur.next.next;
                }else {
                    cur = cur.next;
                }
            }
            node = node.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(2);
        node.next.next.next.next.next = new ListNode(1);

        _面试题_02_01_移除重复节点 cls = new _面试题_02_01_移除重复节点();
        cls.removeDuplicateNodes1(node);
    }
}
