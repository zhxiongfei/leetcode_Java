package 探索头条;

/*
给定一个字符串，逐个翻转字符串中的每个单词。

         

        示例 1：

        输入: "the sky is blue"
        输出: "blue is sky the"
        示例 2：

        输入: "  hello world!  "
        输出: "world! hello"
        解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        示例 3：

        输入: "a good   example"
        输出: "example good a"
        解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
         

        说明：

        无空格字符构成一个单词。
        输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
         

        进阶：

        请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class 翻转字符串里的单词 {

    /**
     *
     * 1，去除多余空格
     * 2, 字符串整体逆序
     * 3, 逐个单词逆序
     *
     * */
    public static String reverseWords(String s) {

        // 去除多余的空格
        char[]chars = s.toCharArray();
        int cur = 0, len = 0;
        // 上一个字符是否为空格字符
        boolean space = true;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != ' '){
                chars[cur ++] = c;
                space = false;
            }else if (space == false){
                chars[cur ++] = c;
                space = true;
            }
        }

        // 有效字符串长度
        // 如果末尾是 ' ' 则为 cur - 1
        // 否则为 cur
        len = space ? cur - 1 : cur;
        if(len <= 0) return "";

        // 整体反转字符串
        reverse(chars, 0, len);

        // 逐个单词再次反转
        int prevSpaceIdx = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars,prevSpaceIdx + 1,i);
            prevSpaceIdx = i;
        }

        // 反转最后一个单词
        reverse(chars, prevSpaceIdx + 1, len);

        return new String(chars, 0, len);
    }

    // 在 [l, r) 范围内，反转字符串
    public static void reverse(char[] chars, int l, int r){
        r --;
        while (l < r){
            char tmp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = tmp;
        }
    }

    public static void main(String[] args) {
        reverseWords("  are   you   ok  ");
    }

}
