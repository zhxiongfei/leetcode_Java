package 贪心;

/*
假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

        注意：

        你可以假设胃口值为正。
        一个小朋友最多只能拥有一块饼干。

        示例 1:

        输入: [1,2,3], [1,1]

        输出: 1

        解释:
        你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
        虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
        所以你应该输出1。
        示例 2:

        输入: [1,2], [1,2,3]

        输出: 2

        解释:
        你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
        你拥有的饼干数量和尺寸都足以让所有孩子满足。
        所以你应该输出2.

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/assign-cookies
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.lang.reflect.Array;
import java.util.Arrays;

public class _455_分发饼干 {

    // 贪心算法
    // 思路 ：g 和 s 都升序排列。 从小到大遍历。 先最小的蛋糕满足饭量最小的孩子。 循环
    // [1,2,3], [1,1]
    static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;

        int begain = 0;
        for (int i = 0; i < g.length; i++) {
            int gi = g[i];

            for (int j = begain; j < s.length; j++) {
                int si = s[j];

                begain ++;
                if (gi > si) continue;

                res ++;
                break;
            }
        }

        return res;
    }

    public static void main(String[] args){

        int[] g = {1,2,3,4,5,6,7,8};
        int[] s = {1,1,4,7,1,2,3,5};
        int res = findContentChildren(g,s);

        System.out.println(res);
    }
}
