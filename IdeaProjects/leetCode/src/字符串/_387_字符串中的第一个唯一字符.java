package 字符串;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

         

        示例：

        s = "leetcode"
        返回 0

        s = "loveleetcode"
        返回 2
         

        提示：你可以假定该字符串只包含小写字母。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _387_字符串中的第一个唯一字符 {

    /**
     *
     * 哈希表
     * 第一次遍历 哈希表存储元素出现次数
     * 再次遍历字符串 哈希表中存储的次数为1时，则为最终结果
     *
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(n)
     *
     * */
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) return i;
        }
        return -1;
    }

    /**
     *
     * 哈希表优化为数组
     *
     * 因为题目中有限制，字符串只包含小写字母
     * 所以可以使用长度为 26 的数组来存储字母的出现次数
     *
     * */
    public static int firstUniqChar1(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            count[c - 'a'] ++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }


    public static void main(String[] args) {

        int res = firstUniqChar("eetcode");
        System.out.println(res);
    }
}
