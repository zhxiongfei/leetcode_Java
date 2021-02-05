package 滑动窗口;

import java.util.HashMap;

/**
给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
        注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

        示例 1：
        输入：s = "ADOBECODEBANC", t = "ABC"
        输出："BANC"
        示例 2：

        输入：s = "a", t = "a"
        输出："a"
         

        提示：
        1 <= s.length, t.length <= 105
        s 和 t 由英文字母组成

        进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-window-substring
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _76_最小覆盖子串 {

    /**
     * 滑动窗口
     * 与 17_18_最短超串基本一样。
     * 先向右滑动， 直到窗口中包含所有 t 中的元素
     * 再向左滑动， 条件是窗口中仍然呢包含所有 t 中的元素。 尽量向左移动窗口。
     *
     * minLeft 为最小覆盖子串的左边界
     * minRight 为最小覆盖子串的右边界
     * minLen 记录最小覆盖子串的最小值
     * valid 记录窗口中有效的数字个数
     * */

    /**
     * 有一个点
     * Java 的 Integer 比较直接用 == 会有问题。
     * 用 compareTo 来比较
     * */

    /**
        以下链接介绍了
        为什么相同的数值的 Integer， 在使用 == 时,会返回false

        https://blog.csdn.net/andyzhaojianhui/article/details/84324466
     */
    /**
     以下是 leetcode 评论

     我想说的是 Java 用Map记录字母出现个数的写法，
     最后一个测试用例通不过时，要明白一件事。
     Integer是对象啊。。。
     Integer会缓存频繁使用的数值，
     数值范围为-128到127，在此范围内直接返回缓存值。
     超过该范围就会new 一个对象。
     浪费了我两个小时，希望有这种情况的老哥注意一下。
     */

    public String minWindow(String s, String t) {
        HashMap<Character, Integer>need = new HashMap<>();
        HashMap<Character, Integer>window = new HashMap<>();

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();

        for (char c : chars2) need.put(c, need.getOrDefault(c, 0) + 1);
        int left = 0, right = 0;
        int length = s.length(), tLen = need.size();
        int minLeft = 0, minRight = 0, minLen = Integer.MAX_VALUE;
        int valid = 0;

        while (right < length){
            char c = chars1[right ++];
            if (need.containsKey(c) && need.get(c) > 0){
                window.put(c, window.getOrDefault(c, 0) + 1);

                //if (window.get(c) == need.get(c)) valid ++;
                if (window.get(c).compareTo(need.get(c)) == 0) valid ++;
            }

            while (valid == tLen){
                if (minLen > right - left){
                    minLeft = left;
                    minRight = right;
                    minLen = right - left;
                }
                char tmp = chars1[left ++];
                if (need.containsKey(tmp) && need.get(tmp) > 0){
                    window.put(tmp, window.get(tmp) - 1);

//                    if (window.get(tmp) < need.get(tmp)) valid --;
                    if (window.get(tmp).compareTo(need.get(tmp)) < 0) valid --;
                }
            }
        }
        return s.substring(minLeft, minRight);
    }

    public static void main(String[] args) {
        _76_最小覆盖子串 cls = new _76_最小覆盖子串();
        String str = cls.minWindow("aaabbbbbcdd","abcdd");
        System.out.println(str);
    }
}
