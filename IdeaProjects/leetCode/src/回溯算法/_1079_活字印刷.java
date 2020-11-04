package 回溯算法;

import javax.swing.plaf.multi.MultiSeparatorUI;
import java.util.*;

/**
你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。

        注意：本题中，每个活字字模只能使用一次。

         

        示例 1：

        输入："AAB"
        输出：8
        解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
        示例 2：

        输入："AAABBC"
        输出：188
         

        提示：

        1 <= tiles.length <= 7


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-tile-possibilities
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1079_活字印刷 {

    int res = -1;
    public int numTilePossibilities(String tiles) {
        boolean[] used = new boolean[tiles.length() + 1];
        char[]chars = tiles.toCharArray();
        Arrays.sort(chars);
        dfs(chars, used);
        return res;
    }

    private void dfs(char[] chars, boolean[] used){
        res ++;
        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            dfs(chars, used);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        _1079_活字印刷 cls = new _1079_活字印刷();
        cls.numTilePossibilities("AAABBC");
    }
}
