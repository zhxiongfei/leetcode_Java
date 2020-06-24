package 探索系列.探索初级算法;

public class 加一 {

    public int[] plusOne(int[] digits) {

        int carry = 0;

        for (int i = digits.length - 1; i >= 0; i--) {
            int num = digits[i];
            int sum = num + 1 + carry;
            carry = sum / 10;
            digits[i] = sum;

            if (carry == 0) break;
        }

        if (carry == 0) return digits;

        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            ans[i + 1] = digits[i];
        }
        return ans;
    }

}
