package 剑指offer;

/*
在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

        示例:

        s = "abaccdeff"
        返回 "b"

        s = ""
        返回 " "
         

        限制：

        0 <= s 的长度 <= 50000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashMap;

public class _剑指_Offer_50_第一个只出现一次的字符 {

    /**
     *
     * 利用 HashMap
     * 遍历字符串, map中存储 <字符 : index>
     *     遍历过程中，如果 map 中包含 当前字符， 则 map.put(当前字符 : -1),-1表示有重复不符合要求
     *     如果 map 中不包含 当前字符， 则 map.put(当前字符 : i)
     *
     * 字符串遍历完毕
     *     map中保存的就是
     *     重复字符对应的 value 是 -1， 不重复字符对应的value 为 下标
     *
     * 两种方法
     *    一，遍历 字符串，遍历的字符 在 map中对应的value不为 -1时。 则为最终结果
     *     遍历完毕，如果没有找到value不为 -1的。 则返回 ' '
     *
     *    二，要么 遍历 map
     *     记录，不为 -1的，所有value中的最小值，即为最终结果的下标
     *
     * */
    public static char firstUniqChar(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])){
                map.put(chars[i], -1);
            }else {
                map.put(chars[i], i);
            }
        }

//        Object[] objs = map.keySet().toArray();
//        int resIdx = Integer.MAX_VALUE;
//        for (int i = 0; i < objs.length; i++) {
//            if (map.get(objs[i]) == -1) continue;
//            resIdx = Math.min(resIdx, map.get(objs[i]));
//        }
//
//        return resIdx == Integer.MAX_VALUE ? ' ' : chars[resIdx];

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) != -1) return chars[i];
        }

        return ' ';
    }

    /**
     *
     * 优化
     * 因为 s只包含小写字符
     * 所以可以使用数组, 代替哈希表
     *
     * */
    public static char firstUniqChar1(String s) {
        char[] chars = s.toCharArray();

        int[] nums = new int[26];
        for (int i = 0; i < chars.length; i++) {
            nums[chars[i] - 'a'] ++;
        }

        for (int i = 0; i < chars.length; i++) {
            if (nums[chars[i] - 'a'] == 1) return chars[i];
        }

        return ' ';
    }

    public static void main(String[] args) {
        char c = firstUniqChar1("abaccdeff");
        System.out.println(c);
    }


}
