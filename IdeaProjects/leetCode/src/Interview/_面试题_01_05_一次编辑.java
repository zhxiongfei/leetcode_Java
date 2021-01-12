package Interview;

/*
字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
        示例 1:

        输入:
        first = "pale"
        second = "ple"
        输出: True

        示例 2:

        输入:
        first = "pales"
        second = "pal"
        输出: False

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/one-away-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_05_一次编辑 {

    public boolean oneEditAway(String first, String second) {

        int len1 = first.length();
        int len2 = second.length();

        // 长度相差超过1， 一次编辑不能处理
        if (Math.abs(len1 - len2) > 1) return false;

        if (len1 == len2){
            // 长度相同
            // 一次编辑 -> 替换
            int diffcnt = 0;
            for (int i = 0; i < len1; i++) {
                if (first.charAt(i) != second.charAt(i))  diffcnt ++;
            }
            return diffcnt <= 1;
        }else {

            // 保证 first串 比 second串 长
            if (len2 > len1) return oneEditAway(second,first);

            // 长度相差 1
            // 一次编辑 -> 添加/删除
            // 双指针，i用来遍历 first
            // j 用来遍历 second
            int i = 0, j = 0;
            while (i < len1 && j < len2){
                char c1 = first.charAt(i);
                char c2 = second.charAt(j);

                // 相等 两个指针都向右移动一位
                if (c1 == c2){
                    i ++;
                    j ++;
                    continue;
                }

                // 已经有一次不想等，指针后移，再次不想等， 直接return false.
                if (i != j) return false;

                // 第一次不相等
                i ++;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        _面试题_01_05_一次编辑 cls = new _面试题_01_05_一次编辑();

        boolean res = cls.oneEditAway("hore","horse");
        if (res == true){

        }
    }
}
