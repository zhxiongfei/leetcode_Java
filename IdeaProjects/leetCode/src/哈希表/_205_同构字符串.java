package 哈希表;

import java.util.HashMap;

/**
给定两个字符串 s 和 t，判断它们是否是同构的。
        如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
        每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。

        示例 1:
        输入：s = "egg", t = "add"
        输出：true

        示例 2：
        输入：s = "foo", t = "bar"
        输出：false
        示例 3：

        输入：s = "paper", t = "title"
        输出：true

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/isomorphic-strings
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _205_同构字符串 {

    /**
     * map 存储对应关系
     * 遍历过程中， 发现对应关系不一样的， 则return false
     * 遍历完毕， 没有以上情况， 则return true
     *
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(字符集数量)
     * */
    public boolean isIsomorphic(String s, String t) {
        int length = s.length();
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (!map.containsKey(c1)){
                if (map.containsValue(c2)) return false;
                map.put(c1, c2);
                continue;
            }
            if (map.get(c1) != c2) return false;
        }
        return true;
    }

}
