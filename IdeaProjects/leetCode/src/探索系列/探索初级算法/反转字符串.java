package 探索系列.探索初级算法;

public class 反转字符串 {

    /**
     *
     * 迭代
     *
     * */
    public void reverseString1(char[] s) {
        if (s == null || s.length <= 1) return;

        int left = 0, right = s.length - 1;
        while (left < right){
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;

            left ++;
            right --;
        }
    }

    /**
     *
     * 递归
     *
     * */
    public void reverseHelper(char[] s, int i, int j){
        if (i >= j) return;
        reverseHelper(s, i + 1, j - 1);

        char c = s[i];
        s[i] = s[j];
        s[j] = c;
    }

    public void reverseString(char[] s){
        if (s.length <= 1) return;
        reverseHelper(s, 0, s.length - 1);
    }
}
