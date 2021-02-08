package 滑动窗口;

/**
今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。

        在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。

        书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。

        请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
         

        示例：

        输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
        输出：16
        解释：
        书店老板在最后 3 分钟保持冷静。
        感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
         

        提示：

        1 <= X <= customers.length == grumpy.length <= 20000
        0 <= customers[i] <= 1000
        0 <= grumpy[i] <= 1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1052_爱生气的书店老板 {

    // [1,0,1,2,1,1,7,5]
    // [0,1,0,1,0,1,0,1]
    // 3

    /**
     *
     * 滑动窗口
     * 1, 先把老板不生气时的客户数量相加 (grympy[i] == 0 时， 相加 customers[i])
     * 2, 滑动一个长度为 X 的窗口，相加在 X 窗口内且 老板生气的 客户数量 (grympy[i] == 1, 相加 customers[i])
     * 3, 向右滑动窗口，当原来窗口最左端下标 老板生气时， 减去。 新进来窗口最右端下标 老板生气时，加上
     * 4, 滑动到最右端时，最大的结果为最终结果
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(1)
     * */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0, sum = 0, left = 0, right = 0, length = customers.length;
        for (int i = 0; i < length; i++) {
            if (grumpy[i] == 0) res += customers[i];
        }
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 0) continue;
            res += customers[i];
        }
        sum = res;
        for (int i = 1; i <= (length - X); i++) {
            if (grumpy[i - 1] == 1) sum -= customers[i - 1];
            if (grumpy[i + X - 1] == 1) sum += customers[i + X - 1];

            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        _1052_爱生气的书店老板 cls = new _1052_爱生气的书店老板();
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy =    {0,1,0,1,0,1,0,1};
        int res = cls.maxSatisfied(customers, grumpy, 3);
        System.out.println(res);
    }
}
