package 栈;
import java.util.Map;
import java.util.Stack;

public class _901_股票价格跨度 {

    private Stack<Integer> mainStack;
    private Stack<Integer> helperStack;
    public _901_股票价格跨度() {

        mainStack = new Stack<>();
        helperStack = new Stack<>();
    }

    /**
     *
     * 辅助栈 超时了
     * 思路是 加入 price时
     *      当栈为空，或者 price < 栈顶元素， 则将price 入栈, 并设置结果为1
     *      当栈不为空，并且 price >= 栈顶元素时, 主栈中元素 依次 pop进入 辅助栈
     *
     * 辅助栈中保存的，就是比 <= price的元素, 那么结果为 辅助栈.size() + 1(price本身)
     *
     * 时间复杂度 : O(N * 2)
     * 超时了
     *
     * */
    public int next(int price) {
        if (mainStack.isEmpty() || price < mainStack.peek()) {
            mainStack.push(price);
            return 1;
        }
        while (!mainStack.isEmpty() && price >= mainStack.peek()){
            helperStack.push(mainStack.pop());
        }
        int size = helperStack.size() + 1;

        while (!helperStack.isEmpty()){
            mainStack.push(helperStack.pop());
        }
        mainStack.push(price);
        return size;
    }

    /**
     *
     * 单调栈
     * 单调递减栈
     *
     * */
    public int next1(int price) {
        if (mainStack.isEmpty() || price < mainStack.peek()){
            mainStack.push(price);
            helperStack.push(1);
            return 1;
        }

        int res = 1;
        while (!mainStack.isEmpty() && price >= mainStack.peek()){
            res += helperStack.pop();
            mainStack.pop();
        }
        mainStack.push(price);
        helperStack.push(res);
        return res;
    }

    public int next2(int price) {
        if (mainStack.isEmpty() || price < mainStack.peek()){
            mainStack.push(price);
            helperStack.push(1);
            return 1;
        }

        int res = 1;
        while (!mainStack.isEmpty() && price >= mainStack.peek()){
            res += helperStack.pop();
            mainStack.pop();
        }
        mainStack.push(price);
        helperStack.push(res);
        return res;
    }

    public static void main(String[] args) {

        _901_股票价格跨度 cls = new _901_股票价格跨度();
        // ["StockSpanner","next","next","next","next","next","next","next","next","next","next"]
        // [[],[28],[14],[28],[35],[46],[53],[66],[80],[87],[88]]

        int[] nums = {28,24,28,35,46,53,66,80,87,88};
        for (int i = 0; i < nums.length; i++) {
            System.out.println(cls.next1(nums[i]));
        }
    }


}
