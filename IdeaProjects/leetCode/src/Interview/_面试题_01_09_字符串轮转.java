package Interview;

/*
字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。

        示例1:

        输入：s1 = "waterbottle", s2 = "erbottlewat"
        输出：True
        示例2:

        输入：s1 = "aa", "aba"
        输出：False
        提示：

        字符串长度在[0, 100000]范围内。
        说明:

        你能只调用一次检查子串的方法吗？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/string-rotation-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题_01_09_字符串轮转 {

    // 暴力法
    // 如果 s2 是由 s1 旋转而来 则从头到位开始旋转s1
    // 遍历旋转0位(原字串)， 1位... 直到旋转到倒数第二位
    // 如果遍历过程中，有跟s2相同的字符串， 则return true
    // 遍历一遍完后，都没有相同字符串， 则return false
    public boolean isFlipedString1(String s1, String s2) {

        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s1.length()){

            sb.delete(0,sb.length());
            sb.append(s1.substring(i, s1.length()));
            sb.append(s1.substring(0,i));
            if (sb.toString().equals(s2)) return true;
            i++;
        }
        return false;
    }

    // 题目中有说明， 你能只调用一次检查子串的方法吗？
    // 显然上述解题，不符合检查一次字串的要求。拼接了N次字串，比较了N次字串
    // 上述方法时间复杂度 : O(N ^ 2)
    // 空间复杂度 : O(1)
    // 时间复杂度非常高，算法虽过了，但是用时很长
    // 接下来，我们尝试做时间复杂度的优化

    // 思路是 裁剪字串成两个子串
    // 从头到位比较,当字串s1 i位置， 与字串s2的0位置，字符相同时，根据i分割两个字串
    // 如果s1 和 s2 分割后，两部分分别都相同，说明是旋转字串
    // 如果不相同， 则往下遍历下一个与 s2 0位置相同的元素。
    // i遍历完， 即与s2 0号位置相同的字符又两部分分别相同的字串。 则return false

    // 我们的时间复杂度有了大幅度的提升，截图中可以看出，由之前的40ms左右，提高到1ms左右。
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public boolean isFlipedString2(String s1, String s2){

        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        int i = 0,len = s1.length();
        while ((i < len)){
            if (s1.charAt(i) != s2.charAt(0)){
                i ++;
                continue;
            }

            String l_s1 = s1.substring(0,i);
            String r_s1 = s1.substring(i,len);

            String l_s2 = s2.substring(0,len - i);
            String r_s2 = s2.substring(len - i,len);

            if (l_s1.equals(r_s2) && r_s1.equals(l_s2)) return true;
            i++;
        }

        return false;
    }

    // 以下为leetcode大神题解思路。
    // 如果 s2 是由 s1旋转而成， 则 s1 + s1 必然包含 s2
    // 想到了字符串相加有可能计算出结果，没想到这种思路
    // 感觉费半天劲，牛逼的思路一行代码搞定了，差距非常大啊 😓
    // 嗯，非常6，双百操作!!!
    public boolean isFlipedString(String s1, String s2){
        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;

        return (s1 + s1).contains(s2);
    }

    public static void main(String[] args) {
        _面试题_01_09_字符串轮转 cls = new _面试题_01_09_字符串轮转();
        cls.isFlipedString("waterbottle","erbottlewat");
    }
}
