package 贪心;

/**
给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。

        （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

        示例 1:

        输入: N = 10
        输出: 9
        示例 2:

        输入: N = 1234
        输出: 1234
        示例 3:

        输入: N = 332
        输出: 299
        说明: N 是在 [0, 10^9] 范围内的一个整数。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/monotone-increasing-digits
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _738_单调递增的数字 {

    /**
     贪心思路
     ①．要使目标数字和 N 最接近，则需要尽可能保持高位数字不动
     ②．若某高位数字减 1，则后续所有低位数字都可直接更改为 9，可保持最接近
     ③．若低位数字更改为 9，则前一高位数字至少得减 1，可保持最接近
     */
    public int monotoneIncreasingDigits(int N) {
        if (N < 10) return N;
        char[] chars = String.valueOf(N).toCharArray();

        int index = 0, size = chars.length;
        while (index < size && chars[index - 1] <= chars[index]) index ++;

        if (index < size){
            while (index > 0 && chars[index - 1] > chars[index]){
                chars[index - 1] --;
                index --;
            }
            for (index ++; index < size; index ++){
                chars[index] = '9';
            }
        }
        return Integer.parseInt(new String(chars));
    }

}
