package 双指针;

/**
你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

        你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

         

        示例 1：

        输入：name = "alex", typed = "aaleex"
        输出：true
        解释：'alex' 中的 'a' 和 'e' 被长按。
        示例 2：

        输入：name = "saeed", typed = "ssaaedd"
        输出：false
        解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
        示例 3：

        输入：name = "leelee", typed = "lleeelee"
        输出：true
        示例 4：

        输入：name = "laiden", typed = "laiden"
        输出：true
        解释：长按名字中的字符并不是必要的。
         

        提示：

        name.length <= 1000
        typed.length <= 1000
        name 和 typed 的字符都是小写字母。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/long-pressed-name
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _925_长按键入 {

    /**
     * 双指针
     * */
    public static boolean isLongPressedName(String name, String typed) {
        if (name == null && typed == null) return true;
        if (name.length() == 0 && typed.length() == 0) return true;
        if (name == null || typed == null || name.length() == 0 || typed.length() == 0) return false;
        if (name.length() > typed.length()) return false;
        if (typed.charAt(0) != name.charAt(0)) return false;

        char prev = typed.charAt(0);
        int i = 1, j = 1;
        while (j < typed.length()){
            char nc = name.charAt(i >= name.length() ? name.length()-1 : i);
            char tc = typed.charAt(j);

            if (nc == tc){
                i ++;
                j ++;
                prev = nc;
                continue;
            }
            if (tc == prev){
                j ++;
                continue;
            }
            return false;
        }
        return i >= name.length() || (i == name.length() - 1 && name.charAt(i) == typed.charAt(j - 1));
    }

    public static void main(String[] args) {

//        String s1 = "pyplrz";
//        String s2 = "ppyypllr";

        String s1 = "vtkgn";
        String s2 = "vttkgnn";

        boolean res = isLongPressedName(s1, s2);
        System.out.println(res);
    }

}
