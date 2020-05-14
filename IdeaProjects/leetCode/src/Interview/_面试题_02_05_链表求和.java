package Interview;

/*
给定两个用链表表示的整数，每个节点包含一个数位。

        这些数位是反向存放的，也就是个位排在链表首部。

        编写函数对这两个整数求和，并用链表形式返回结果。

         

        示例：

        输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
        输出：2 -> 1 -> 9，即912
        进阶：假设这些数位是正向存放的，请再做一遍。

        示例：

        输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
        输出：9 -> 1 -> 2，即912

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sum-lists-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_02_05_链表求和 {


    // 串链表
    // 初始化 newHead 作为新链表的虚拟头结点
    // 变量 carry 记录是否上一个值有无进位
    // 当l1或者l2不为空时， 遍历l1 和 l2
    // 当前和为 l1.val + l2.val + carry
    // 根据sum创建node，并串在newHead上
    // 到末尾，如果carry > 0 代表需要末尾拼上 carry
    // 最终返回 newHead.next
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        int carry = 0;
        while (l1 != null || l2 != null){
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = 0;
            if (sum >= 10){
                sum -= 10;
                carry = 1;
            }

            node.next = new ListNode(sum);
            node = node.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry > 0){
            node.next = new ListNode(carry);
        }

        return newHead.next;
    }
}
