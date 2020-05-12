package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class _面试题_01_02_判定是否互为字符重排 {

    // 1,分别对两个字符串char数组
    // 2,排序两个数组
    // 3,循环比较两个数组的每一位，不一致return false
    // 4,比较到最后，都相同。 return true
    public boolean CheckPermutation(String s1, String s2) {

        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        char[]c1 = s1.toCharArray();
        char[]c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }

    // 用数组存储，字符串中每个字符的出现次数。
    // 遍历字符串
    // s1中存在的字符， 数组对应位置 + 1
    // s2中存在的字符， 数组对应位置 - 1
    // 最后，遍历数组，有不为0的位置，则不是重排
    // 所有位置都为0， 则true
    public boolean CheckPermutation1(String s1, String s2){
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int[] tmp = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            tmp[s1.charAt(i)] ++;
            tmp[s2.charAt(i)] --;
        }

        for (int i = 0; i < 256; i++) {
            if (tmp[i] != 0) return false;
        }

        return true;
    }
}
