package 字符串;

/**
给定一个Excel表格中的列名称，返回其相应的列序号。

        例如，

        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...
        示例 1:

        输入: "A"
        输出: 1
        示例 2:

        输入: "AB"
        输出: 28
        示例 3:

        输入: "ZY"
        输出: 701

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/excel-sheet-column-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _171_Excel表列序号 {

    // AB
    public static int titleToNumber(String s) {
        if (s == null || s.length() == 0) return 0;

        int res = 0;
        char[]chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int num = chars[i] - 'A' + 1;
            res = res * 26 + num;
        }
        return res;
    }

    public static void main(String[] args) {
         int res = titleToNumber("AB");
        System.out.println(res);
    }
}
