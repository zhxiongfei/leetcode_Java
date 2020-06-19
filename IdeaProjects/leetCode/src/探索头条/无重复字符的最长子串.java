package 探索头条;

import java.util.HashSet;
import java.util.Set;

public class 无重复字符的最长子串 {

    /**
     *
     * 暴力法
     * 两层循环
     *
     * 外层的 i 是以 i开头的字符串的无重复最长子串
     *
     * 依次往后比较，以每个字符开头的无重复子串的最长长度
     *
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(N)
     *
     * */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int max = 1;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.clear();
            set.add(s.charAt(i));
            for (int j = i + 1; j < s.length(); j++) {
                if (set.contains(s.charAt(j))){
                    max = Math.max(max, set.size());
                    break;
                }else {
                    set.add(s.charAt(j));
                    max = Math.max(max, set.size());
                }
            }
        }
        return max;
    }

    /**
     *
     * 上述方法时间复杂度为 O(N ^ 2)
     * 算法提交后，虽然ac了。但是执行耗时特别长。
     * 下边我们来做一下，时间复杂度的优化
     *
     * 滑动窗口
     * [left right] 分别为窗口的 开始位置和结束位置
     * 所以，当前窗口的元素个数就是 right - left
     * */

    // 思路是逐步计算 以 第一个字符开头的最长无重复子串，第二个字符开头的...直到末尾
    public static int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;

        // set用来记录窗口中，的元素
        Set set = new HashSet();
        // set中添加 第一个字符
        set.add(s.charAt(0));

        // 初始化窗口 left=0, right=1相当于 窗口中只有一个 a
        int res = 1,left = 0, right = 1;

        // 循环遍历条件 窗口最右侧到达 字符串最右端
        while (right < s.length()){
            // 窗口中元素 不包含 right指向的元素
            if (!set.contains(s.charAt(right))){
                // set中添加 right指向的元素
                set.add(s.charAt(right));
                // 计算当前窗口的值
                res = Math.max(res,right - left + 1);

                // 窗口右侧范围扩大
                right ++;
            }else {
                // 窗口元素中包含 right指向的元素
                // 窗口右移 -> 直到不包含right指向元素
                set.remove(s.charAt(left++));
            }
        }
        return res;
    }



    public static void main(String[] args) {
        String s = "pwwkew";
        lengthOfLongestSubstring(s);
    }
}
