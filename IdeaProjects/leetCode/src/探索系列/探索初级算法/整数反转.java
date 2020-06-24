package 探索系列.探索初级算法;

public class 整数反转 {

    public static int reverse(int x) {
        if (x >= 0 && x < 10) return x;

        boolean isNegative = false;
        if (x < 0) isNegative = true;

        x = Math.abs(x);
        String string = String.valueOf(x);
        int left = 0, right = string.length() - 1;

        char[] chars = string.toCharArray();
        while (left < right){
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left ++;
            right --;
        }

        String newString = String.valueOf(chars);
        try{
            x = Integer.parseInt(newString);
        }catch (Exception e){
            return 0;
        }
        if (isNegative) x *= -1;
        return  x;
    }

    public static int reverse1(int x) {

        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {

        reverse(123);
    }

}
