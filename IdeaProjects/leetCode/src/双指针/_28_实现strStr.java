package 双指针;

/*
实现 strStr() 函数。

        给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

        示例 1:

        输入: haystack = "hello", needle = "ll"
        输出: 2
        示例 2:

        输入: haystack = "aaaaa", needle = "bba"
        输出: -1
        说明:

        当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

        对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/implement-strstr
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _28_实现strStr {

    // 暴力算法
    public static int strStr1(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;

        int i = 0 , j = 0;
        int len1 = haystack.length();
        int len2 = needle.length();
        while (i < len1 && j < len2){
            if (haystack.charAt(i) != needle.charAt(j)){
                i -= j - 1;
                j = 0;
                continue;
            }

            if (j == needle.length() - 1) {
                System.out.println(i+1-needle.length());
                return i+1-needle.length();
            }

            i ++;
            j ++;
        }

        return -1;
    }

    private static int[] next(String pattern){
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];

        // n == next[i]
        next[0] = -1;
        int i = 0;
        int n = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                ++i;
                ++n;

                if (chars[i] == chars[n]) {
                    next[i] = next[n];
                } else {
                    next[i] = n;
                }
            } else {
                n = next[n];
            }
        }
        return next;
    }

    // KMP
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;

        int[] next = next(needle);

        int i = 0 , j = 0;
        int len1 = haystack.length();
        int len2 = needle.length();
        while (i < len1 && j < len2){
            if (i < 0 || haystack.charAt(i) == needle.charAt(j)){
                i ++;
                j ++;
            }else {
                i = next[i];
            }
        }

        return j == len2 ? (i - j) : -1;
    }

    public static void main(String[] args){
        System.out.println(strStr("mississippi","issip"));
    }
}
