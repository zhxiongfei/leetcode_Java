package 每日一题;

/**
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 *
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 *
 * 请你计算 最多 能喝到多少瓶酒。
 *
 * 输入：numBottles = 9, numExchange = 3
 * 输出：13
 * 解释：你可以用 3 个空酒瓶兑换 1 瓶酒。
 * 所以最多能喝到 9 + 3 + 1 = 13 瓶酒。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/water-bottles
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * */

public class _1518_换酒问题 {

    public static void main(String[] args) {
        numWaterBottles(9,3);
    }

    public static int numWaterBottles(int numBottles, int numExchange) {

        int res = numBottles;

        int leave = res;
        while (leave >= numExchange){
            int add = leave / numExchange;
            res += add;
            leave = leave % numExchange + add;
        }

        return res;
    }

}
