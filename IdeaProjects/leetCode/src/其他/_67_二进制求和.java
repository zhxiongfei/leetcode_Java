package 其他;

/*
给定两个二进制字符串，返回他们的和（用二进制表示）。

        输入为非空字符串且只包含数字 1 和 0。

        示例 1:

        输入: a = "11", b = "1"
        输出: "100"
        示例 2:

        输入: a = "1010", b = "1011"
        输出: "10101"

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/add-binary
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _67_二进制求和 {

    static String addBinary(String a, String b) {

        if(a == null || a.length() == 0) return b;
        if(b == null || b.length() == 0) return a;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int idx1 = a.length() - 1;
        int idx2 = b.length() - 1;
        while (idx1 >= 0 || idx2 >= 0){

            int v1 = (idx1 < 0) ? 0 : a.charAt(idx1) - '0';
            int v2 = (idx2 < 0) ? 0 : b.charAt(idx2) - '0';

            int tmp = v1 + v2 + carry;
            carry = tmp / 2;

            sb.insert(0,tmp % 2);

            idx1 --;
            idx2 --;
        }

        if (carry == 1){
            sb.insert(0,1);
        }

        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(addBinary("111","1"));
    }
}
