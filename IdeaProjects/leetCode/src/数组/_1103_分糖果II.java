package 数组;

/*
排排坐，分糖果。

        我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。

        给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。

        然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。

        重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。

        返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。

         

        示例 1：

        输入：candies = 7, num_people = 4
        输出：[1,2,3,1]
        解释：
        第一次，ans[0] += 1，数组变为 [1,0,0,0]。
        第二次，ans[1] += 2，数组变为 [1,2,0,0]。
        第三次，ans[2] += 3，数组变为 [1,2,3,0]。
        第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
        示例 2：

        输入：candies = 10, num_people = 3
        输出：[5,2,3]
        解释：
        第一次，ans[0] += 1，数组变为 [1,0,0]。
        第二次，ans[1] += 2，数组变为 [1,2,0]。
        第三次，ans[2] += 3，数组变为 [1,2,3]。
        第四次，ans[0] += 4，最终数组变为 [5,2,3]。
         

        提示：

        1 <= candies <= 10^9
        1 <= num_people <= 1000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/distribute-candies-to-people
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 *
 * 看到 candies的取值范围 1<= candies <= 10^9
 * 就不可能用暴力法了。 绝对超时
 *
 * 能想到等差数列，求总共分配多少次。
 * 再用次数，和等差数列公式求出最终结果
 * 就是代码写不太好
 * 这种数学公式的题目，也没台细细琢磨
 * 大致懂意思， copy了一份代码。
 * 完事了
 *
 * */
public class _1103_分糖果II {

    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];

        // 求出总共分配多少次
        int p = (int)(Math.sqrt(2 * candies + 0.25) - 0.5);

        // 最后一次的余量
        int remaining = (int)(candies - (p + 1) * p * 0.5);

        int row = p / num_people, col = p % num_people;
        for (int i = 0; i < num_people; i++) {
            // complete rows
            res[i] = (i + 1) * row + (int)(row * (row - 1) * 0.5) * num_people;
            // cols in the last row
            if (i < col) res[i] += i + 1 + row * num_people;
        }
        res[col] += remaining;

        return res;
    }
    
}
