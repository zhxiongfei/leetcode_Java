package Interview;


/*
实现一个算法，确定一个字符串 s 的所有字符是否全都不同。

        示例 1：

        输入: s = "leetcode"
        输出: false
        示例 2：

        输入: s = "abc"
        输出: true
        限制：

        0 <= len(s) <= 100
        如果你不使用额外的数据结构，会很加分。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/is-unique-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


import java.util.HashSet;

public class _面试题_01_01_判定字符是否唯一 {

    // 拿到这道题第一反应是，从头到尾遍历元素，用set存储遍历过的元素
    // 继续遍历时，如果set中保存了，说明重复了
    // 如果set中没有保存，则将当前元素加入set中
    // 遍历完整个字符串，都没重复，说明没有重复
    // 算法时间复杂度 O(N)遍历一遍字符串。
    // 空间复杂度O(N) 使用了额外的set存储空间
    public boolean isUnique1(String astr) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i++) {
            char c = astr.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    // 但是题目中有限制
    // 如果不使用额外的数据结构，会很加分
    // 如何不使用额外的数据结构，时间复杂度还尽量低呢？
    // 不使用set，而使用暴力解法？
    // 可以，但是时间复杂度  O(N ^ 2)。
    public boolean isUnique2(String astr) {
        for (int i = 0; i < astr.length() - 1; i++) {
            char c1 = astr.charAt(i);
            for (int j = i + 1; j < astr.length(); j++) {
                char c2 = astr.charAt(j);

                if (c1 == c2) return false;
            }
        }
        return true;
    }

    // 基于bool数组的方法
    // 猜测题目中的字符为26个字母
    // 初始化数组存储26个元素，初始值都为0
    // 一层循环，取出对应字符 - 'a' 对应数组的下标
    // 取出该下标的元素，如果值为1， 则重复，返回false
    // 如果值为0， 则将其置为1。
    // 如果遍历完整个字串，都没有重复，则返回true
    // 时间复杂度O(N), 空间复杂度为O(1)常数阶
    // 但是仍然使用了 额外的数据结构 数组
    public boolean isUnique3(String astr) {
        boolean[] bools = new boolean[26];
        for (int i = 0; i < astr.length(); i++) {
            int index = astr.charAt(i) - 'a';
            if (bools[index] == true) return false;
            bools[index] = true;
        }
        return true;
    }

    // leetcode大神思路
    // 基于位运算的方法
    // 使用一个int类型的变量，来替代长度为26的bool数组。
    // 假设这个变量占26位(java中占32位)， 则26为都初始化为0
    // 26位对应26个字符
    // 遍历字符串
    // 首先计算当前字符与'a'的距离，用move_bit表示
    // 那么使用左移运算符 1<<move_bit 可以得到对应下标为1，其余下标为0的数
    // 用位运算mark | (1 << mov_bit) 将对应下标置为1
    public boolean isUnique(String astr){
        int mark = 0;
        for (int i = 0; i < astr.length(); i++) {
            int move_bit = 1<<(astr.charAt(i) - 'a');
            if ((mark & move_bit) != 0) return false;
            mark |= move_bit;
        }
        return true;
    }
}
