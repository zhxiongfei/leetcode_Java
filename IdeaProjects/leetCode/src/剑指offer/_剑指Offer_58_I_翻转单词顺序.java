package 剑指offer;

public class _剑指Offer_58_I_翻转单词顺序 {

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

}
