package 双指针;

/*
给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。

        示例 1:

        输入: "aba"
        输出: True
        示例 2:

        输入: "abca"
        输出: True
        解释: 你可以删除c字符。
        注意:

        字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-palindrome-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _680_验证回文字符串Ⅱ {

    /*
    * 思路：
    * 如果没有可删除一个元素的话，这道题很简单
    * 利用双指针，分别指向开始和末尾，比较指向元素是否相同
    * 相同，则指针分别向前，向后移动一位
    * 不同，则不是回文串
    *
    * 删除一个元素
    * 也利用双指针i，j分别指向开始和末尾
    * 1，当比较不同时，则把i向右移动一位，继续比较。
    * 2，还是不同的话，则把j向左移动一位，继续比较。
    * 1 和 2中有一个相同时，则为回文
    * 都不同时，则不是回文字串
    * */
    public static boolean validPalindrome(String s) {

        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j && chars[i] == chars[j]){
            i ++;
            j --;
        }

        // 删除左边元素
        if (isValid(chars, i + 1, j)) return true;

        // 删除右边元素
        if (isValid(chars, i, j - 1)) return true;

        return false;
    }

    //    [a, b , b, a]
    public static boolean isValid(char[] chars, int i, int j){
        while (i < j){
            if (chars[i ++] != chars[j --]) return false;
        }
        return true;
    }
}
