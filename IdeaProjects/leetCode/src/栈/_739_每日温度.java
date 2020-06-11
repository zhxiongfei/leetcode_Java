package 栈;

import java.util.Stack;

public class _739_每日温度 {

    /**
     *
     * 暴力法
     *
     * 从头到位遍历元素，取到当前元素与其后边元素挨个比较
     *      当后边元素 <= 当前元素时，继续向后遍历
     *      当 后边元素 > 当前元素时，当前元素升高需要 j - i天 (j , i分别代表后边元素 和 当前元素的下标)
     *      遍历到最后，一只没有比当前元素大的温度，则其为0
     *
     * */
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length - 1; i++) {
            int n1 = T[i];
            for (int j = 0; j < T.length; j++) {
                if (T[j] > n1){
                    res[i] = j - j;
                    break;
                }
            }
        }
        return res;
    }

    /**
     *
     * - 根据题意，从最后一天推到第一天，会简单很多。第一天显然没有升高的可能，结果为0.
     * - 再看倒数第二天，如果比倒数第一天低， 则为1。如果比倒数第一天高，则也为0
     * - 由此可见。 求第 i 天的天数时
     *   - 如果 T[i + 1] > T[i], 则 res[i] = 1
     *   - 如果 T[i + 1] < T[i]
     *     - 如果 res[i + 1] == 0, 则表明 i + 1 以后不会再升高。 所以 res[i] = 0
     *     - 如果 res[i + 1] != 0, 那么就比较 T[i] 和 T[i + i + res[i + 1]] (也就是比较 第 i 天的温度 和 比 i+1 天 温度高的那天的温度)
     *
     * */
    public int[] dailyTemperatures1(int[] T) {
        // 存放结果的数组
        int[] res = new int[T.length];

        // 倒是第一天，肯定不会升高， 为0。
        // 可以省略这句，因为int[] 在 java中每个元素初始值就是 0
        res[T.length - 1] = 0;

        // 从倒数第二天开始计算升高的天数
        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < T.length){
                // 如果 i + 1天 > i天。 则res[i] 为 i
                if (T[j] > T[i]){
                    res[i] = j - i;
                    break;
                }else if (res[j] == 0){
                    // 如果 i + 1天 <= i天。
                    // 并且 res[i + 1] == 0， 说明i + 1以后不会升高。 所以 res[i] = 0
                    res[i] = 0;
                    break;
                }else {
                    // 否则，while循环继续比较 i + 1 + res[i + 1] 位置 和 i位置的大小
                    j += res[j];
                }
            }
        }
        return res;
    }

    /**
     *
     *题目的标签是栈，那么我们是否可以利用栈来解决问题呢？答案是可以的，利用**单调递减栈**
     *
     * - 初始化一个 栈 ，并且维护此栈的元素**从栈底到栈顶单调递减**，栈中存放元素的下标
     *
     * - 从头到尾遍历元素
     *   - while循环执行条件： **栈为空 并且 元素 大于 栈顶元素** 时
     *     -  res[stack.peek()] = i - stack.pop();
     *   - while执行完毕，则一定满足 **栈**为空，或者**元素小于等于栈顶元素时**
     *     - 这时，将元素的下标直接入栈
     *
     * */
    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        // 单调递减栈, 存放元素下标
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            // 栈不为空 且 当前元素 > 栈顶元素
            while (!stack.isEmpty() && T[stack.peek()] < T[i]){
                res[stack.peek()] = i - stack.pop();
            }

            // while循环执行完毕，一定满足 两个条件:栈为空 或者 当前元素 <= 栈顶元素 两者之一
            // 直接把当前元素的 下标 入栈
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        _739_每日温度 test = new _739_每日温度();

        int[] t = {55,38,53,81,61,93,97,32,43,78};
        int[] res = test.dailyTemperatures(t);
        System.out.println(res.toString());
    }
}
