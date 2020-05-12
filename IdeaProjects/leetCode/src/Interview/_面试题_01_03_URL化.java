package Interview;

/*
URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）

        示例1:

        输入："Mr John Smith    ", 13
        输出："Mr%20John%20Smith"
        示例2:

        输入："               ", 5
        输出："%20%20%20%20%20"
        提示：

        字符串长度在[0, 500000]范围内。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/string-to-url-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_03_URL化 {

    // Java 库函数
    public String replaceSpaces(String S, int length) {
        return S.substring(0,length).replace(" ","%20");
    }

    // StringBuilder拼接字符串
    public String replaceSpaces2(String S, int length){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (S.charAt(length) == ' '){
                // 空格
                sb.append("%20");
            }else {
                sb.append(S.charAt(i));
            }

        }
        return sb.toString();
    }


    // 转字符数组
    public String replaceSpaces1(String S, int length) {
        char[] chars = S.toCharArray();

        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' '){
                chars[index --] = '0';
                chars[index --] = '2';
                chars[index --] = '%';
            }else {
                chars[index --] = chars[i];
            }
        }

        while (index >= 0){
            chars[index --] = ' ';
        }

        return String.valueOf(chars).trim();
    }
}
