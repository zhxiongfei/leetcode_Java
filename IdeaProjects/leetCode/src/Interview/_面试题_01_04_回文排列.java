package Interview;
/*
给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。

        回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。

        回文串不一定是字典当中的单词。

         

        示例1：

        输入："tactcoa"
        输出：true（排列有"tacocat"、"atcocta"，等等）

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;

public class _面试题_01_04_回文排列 {

    // 能排列成回文排列的, 有两种情况
    // 1， 每个字符都是两个
    // 2， 中间字符1个，其余字符都是两个
    // 所以 遍历字符串，Set中不包含元素时，添加。
    // Set中包含元素时，把包含的元素删除(抵消)
    // 最终Set中有 0个 或者 1个元素。则为回文排列
    public boolean canPermutePalindrome(String s) {

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)){
                set.remove(c);
            }else {
                set.add(c);
            }
        }

        return set.size() <= 1;
    }
}
