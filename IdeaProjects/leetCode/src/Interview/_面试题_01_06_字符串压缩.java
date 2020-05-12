package Interview;

/*
字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。

        示例1:

        输入："aabcccccaaa"
        输出："a2b1c5a3"
        示例2:

        输入："abbccd"
        输出："abbccd"
        解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
        提示：

        字符串长度在[0, 50000]范围内。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/compress-string-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_06_字符串压缩 {

    // 非常基础的题目
    // StringBuilder拼接字符串
    // 从头到尾遍历 字符串
    // 上一次遍历的不一致时，记录上一个的次数。
    // 并重置次数， 且prev 置为新字符
    // 最终返回 新字符 和 旧字符 两者中，较短者
    public String compressString(String S) {

        if (S == null || S.length() <= 2) return S;

        char prev = S.charAt(0);
        int sum = 1;

        StringBuilder sb = new StringBuilder();
        sb.append(prev);

        for (int i = 1; i < S.length(); i++) {
            if (prev == S.charAt(i)){
                sum ++;
                continue;
            }

            sb.append(sum);

            prev = S.charAt(i);
            sum = 1;

            sb.append(prev);
        }

        String result = sb.toString();
        if(result.length() >= S.length()) return S;
        return result;
    }
}
