package 探索系列.探索初级算法;

public class 加一 {

    public static void main(String[] args) {
        int[] digits = {9,9};
        int[] res = plus(digits);
        System.out.println(res);
    }

    private static int[] plus(int[] digits){
        int index = digits.length - 1, carry = 0;
        while(index >= 0){
            int cur = digits[index];
            cur = cur + carry + (index == digits.length - 1 ? 1 : 0);
            carry = 0;
            if (cur >= 10){
                cur -= 10;
                carry = 1;
            }
            digits[index] = cur;

            if (carry == 0) break;
            index --;
        }

        if (carry == 0) return digits;
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        for (int i = 0; i < digits.length; i ++){
            res[i + 1] = digits[i];
        }
        return res;
    }

}
