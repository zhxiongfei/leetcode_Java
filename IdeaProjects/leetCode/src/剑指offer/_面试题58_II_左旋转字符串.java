package 剑指offer;

/*
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

         

        示例 1：

        输入: s = "abcdefg", k = 2
        输出: "cdefgab"
        示例 2：

        输入: s = "lrloseumgh", k = 6
        输出: "umghlrlose"
         

        限制：

        1 <= k < s.length <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题58_II_左旋转字符串 {

    // 直接用 substring 切割字符串
    public String reverseLeftWords(String s, int n) {
        if (n == 0 || s == null || s.length() == 0) return s;

        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(n, s.length()));
        sb.append(s.substring(0, n));

        return sb.toString();
    }

    // 如果不允许用 substring
    // 则遍历，挨个拼接
    public String reverseLeftWords1(String s, int n) {
        if (n == 0 || s == null || s.length() == 0) return s;

        StringBuilder sb = new StringBuilder();
        for(int i = n; i < s.length(); i++)
            sb.append(s.charAt(i));
        for(int i = 0; i < n; i++)
            sb.append(s.charAt(i));
        return sb.toString();
    }

    // 可以用取余简化代码
    public String reverseLeftWords2(String s, int n) {
        if (n == 0 || s == null || s.length() == 0) return s;

        StringBuilder sb = new StringBuilder();
        for(int i = n; i < s.length() + n; i++)
            sb.append(s.charAt(i % s.length()));
        return sb.toString();
    }

}

