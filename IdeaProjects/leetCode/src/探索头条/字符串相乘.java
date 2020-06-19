package 探索头条;

public class 字符串相乘 {

    public String multiply(String num1, String num2) {

        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        StringBuilder sb = new StringBuilder();
        int carry = 0, i = 0, j = 0;
        while (i < chars1.length && j < chars2.length){
            int n1 = chars1[i] - '0';
            int n2 = chars2[j] - '0';

            int num = n1 * n2 + carry;
            carry = num / 10;
            num %= 10;

            sb.append(num);
        }
        if (i < chars1.length){

        }else if (j < chars2.length){

        }

        return sb.toString();
    }

}
