package 探索系列.探索初级算法;

public class 反转字符串 {

    public void reverseString(char[] s) {
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

}
