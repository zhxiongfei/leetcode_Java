package 字符串;

/**
给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

        示例 1:

        输入: num1 = "2", num2 = "3"
        输出: "6"
        示例 2:

        输入: num1 = "123", num2 = "456"
        输出: "56088"
        说明：

        num1 和 num2 的长度小于110。
        num1 和 num2 只包含数字 0-9。
        num1 和 num2 均不以零开头，除非是数字 0 本身。
        不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/multiply-strings
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _43_字符串相乘 {

    /**
     * 字符串相加
     * */
    public String addString(String s1, String s2){
        int i = s1.length() - 1, j = s2.length() - 1, carry = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0){
            int n1 = i < 0 ? 0 : s1.charAt(i--) - '0';
            int n2 = j < 0 ? 0 : s2.charAt(j--) - '0';
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sum %= 10;
            stringBuilder.append(sum);
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * 字符串相乘
     * 思路是 ： num1逐位 和 num2相乘， 再相加
     * */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        String s1 = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = num2.length() - 1; j > i; j--) {
                stringBuilder.append(0);
            }
            int n1 = num2.charAt(i) - '0',carry = 0, j = num1.length() - 1, multiply = num2.length() - 1 - i;
            while (j >= 0 || carry > 0){
                int n2 = j < 0 ? 0 : num1.charAt(j --) - '0';
                int res = n1 * n2 + carry;
                carry = res / 10;
                res %= 10;
                stringBuilder.append(res);
            }
            s1 = addString(s1, stringBuilder.reverse().toString());
        }
        return s1;
    }

    public static void main(String[] args) {
        String s1 = "12345432";
        String s2 = "12345432";

        _43_字符串相乘 cls = new _43_字符串相乘();
        String res = cls.multiply(s1, s2);
        System.out.println(res);
    }
}
