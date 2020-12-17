package 哈希表;

import java.util.HashMap;

/**
给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

        这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

        示例1:

        输入: pattern = "abba", str = "dog cat cat dog"
        输出: true
        示例 2:

        输入:pattern = "abba", str = "dog cat cat fish"
        输出: false
        示例 3:

        输入: pattern = "aaaa", str = "dog cat cat dog"
        输出: false
        示例 4:

        输入: pattern = "abba", str = "dog dog dog dog"
        输出: false
        说明:
        你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。   

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/word-pattern
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _290_单词规律 {

    /**
     * 思路 :
     * 1, 将字符串转化成 字符串数组
     * 2, 使用 HashMap 记录字符串对应的字符 <字符串 : 字符>
     * 3, 遍历 字符串数组
     * 4, 当 map key 中包含 字符串时
     *    取出对应的 value
     *    与模式串第 i 位比较
     *    不相同时 return false
     * 5, 当 map key 中不包含 字符串时
     *    如果 map 的 values 中包含 模式串 第 i 位， return false
     *    否则，map.put(str, pat)
     * 6, 遍历完成都没有进入 return false。 则说明符合条件， return true;
     *
     * 7, 需要注意一点 --> 当字符串数组的 length != 模式串length 时. 直接返回 false。 因为不可能匹配上.
     * */
    public boolean wordPattern(String pattern, String s) {
        String[] strings = s.split(" ");
        HashMap<String, Character> map = new HashMap<>();
        if (strings.length != pattern.length()) return false;

        for (int i = 0; i < strings.length; i++) {
            String str = strings[i];
            char pat = pattern.charAt(i);
            if (map.containsKey(str)){
                char c = map.get(str);
                if (c != pat) return false;
            }else {
                if (map.values().contains(pat)) return false;
                map.put(str, pat);
            }
        }
        return true;
    }

}
