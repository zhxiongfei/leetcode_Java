package 字符串;

/*
给定两个字符串, A 和 B。

        A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后，A 能变成B，那么返回True。

        示例 1:
        输入: A = 'abcde', B = 'cdeab'
        输出: true

        示例 2:
        输入: A = 'abcde', B = 'abced'
        输出: false

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/rotate-string
 */

public class _796_旋转字符串 {

    public boolean rotateString(String A, String B){
        if (A == null || B == null) return false;
        if (A.length() != B.length()) return false;

        return (A + A).contains(B);
    }
}
