package 未分组;

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

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _151_翻转字符串里的单词 {

    public static String reverseWords(String s) {
        if (s == null) return s;
        // cur 指针指向当前重组后的字符串当前的index
        int cur = 0;
        // len 代表重组后的字符串的有效长度
        int len = 0;

        char[] chars = s.toCharArray();

        // 上一个是否是空格，如果是空格，则下次进入空格时 cur不加加
        boolean prevIsSpace = true;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != ' '){
                chars[cur ++] = chars[i];
                prevIsSpace = false;
            }else if (prevIsSpace == false){
                chars[cur ++] = chars[i];
                prevIsSpace = true;
            }
        }

        // 算出有效长度
        len = prevIsSpace ? cur - 1 : cur;
        if (len <= 0) return "";

        // 反转整体字符串
        reverse(chars,0,len);

        // 分别反转 每个单词
        int prevSpaceIdx = -1;
        for (int i = 0; i < len; i++) {
            if (chars[i] != ' ') continue;
            reverse(chars,prevSpaceIdx + 1,i);
            prevSpaceIdx = i;
        }
        reverse(chars,prevSpaceIdx + 1,len);

        return new String(chars,0,len);
    }

    // 反转[l,r) 左闭右开
    public static void reverse(char[] chars, int l, int r){
        r --;
        while (l < r){
            char tmp = chars[l];
            chars[l++] = chars[r];
            chars[r--] = tmp;
        }
    }
    
    public static void main(String[] args) {
        String s = reverseWords("the sky is blue");
        System.out.println(s);
    }
}
