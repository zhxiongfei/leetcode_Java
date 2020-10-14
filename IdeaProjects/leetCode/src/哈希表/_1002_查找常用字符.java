package 哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。

        你可以按任意顺序返回答案。

         

        示例 1：

        输入：["bella","label","roller"]
        输出：["e","l","l"]
        示例 2：

        输入：["cool","lock","cook"]
        输出：["c","o"]
         

        提示：

        1 <= A.length <= 100
        1 <= A[i].length <= 100
        A[i][j] 是小写字母

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/find-common-characters
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _1002_查找常用字符 {

    public static List<String> commonChars(String[] A) {

        if (A == null || A.length == 0) return new ArrayList<>();

        int[] cs0 = getCS(A[0]);
        for (int i = 1; i < A.length; i++) {
            int[] cs1 = getCS(A[i]);

            for (int j = 0; j < cs0.length; j++) {
                if (cs0[j] > cs1[j]) cs0[j] = cs1[j];
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < cs0.length; i++) {
            String s = Character.toString('a' + i);
            while (cs0[i] -- > 0){
                res.add(s);
            }
        }
        return res;
    }

    public static int[] getCS(String string){
        int[] ret = new int[26];
        for (char c : string.toCharArray()) {
            ret[c - 'a'] ++;
        }
        return ret;
    }

    public static void main(String[] args) {

        String[] A = {"bella","label","roller"};
        commonChars(A);
    }
}
