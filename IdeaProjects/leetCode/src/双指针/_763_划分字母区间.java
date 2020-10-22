package 双指针;

import java.util.ArrayList;
import java.util.List;

/**
字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。

         

        示例 1：

        输入：S = "ababcbacadefegdehijhklij"
        输出：[9,7,8]
        解释：
        划分结果为 "ababcbaca", "defegde", "hijhklij"。
        每个字母最多出现在一个片段中。
        像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
         

        提示：

        S的长度在[1, 500]之间。
        S只包含小写字母 'a' 到 'z' 。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/partition-labels
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _763_划分字母区间 {

    // S = "ababcbaca defegdehijhklij"

    /**
     * 双指针 + 贪心 + 数组代替map提高效率
     *
     * 1，先遍历找到每个字母出现的最大下标
     * 2，遍历 首位置和最大位置之间的字母，如果其中有下标 > 最大下标 : 挪动最大下标
     * 3，遍历完毕，则找到一个分割区间
     * 4，循环遍历下个区间
     *
     *
     * 时间复杂度 : O(N)
     * 这里需要注意，虽然看起来是 循环里套循环，看起来像是 O(N ^ 2)级别的时间复杂度
     * 但是因为, 外层循环是跳跃的，内部循环也是在区间内，所以仍然是遍历了一遍字符串
     * 总共遍历了两遍字符串
     *
     * 控件复杂度 : 常数级别，使用了 一个长度为26的int类型的数组
     * */
    static int count = 0;
    public static List<Integer> partitionLabels(String S) {

        List<Integer>list = new ArrayList<>();
        if (S == null || S.length() == 0) return list;

        int[] lasts = new int[26];
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lasts[chars[i] - 'a'] = i;
        }

        int prev = 0;
        while (prev < chars.length){
            int last = lasts[chars[prev] - 'a'];
            int j = 0;
            while (j++ < last){
                count ++;
                int newLast = lasts[chars[j] - 'a'];
                if (newLast > last) last = newLast;
            }
            list.add(last - prev + 1);
            prev = last + 1;
        }
        return list;
    }

    /**
     * 题解区域看到比较有意思的解法,大致思想和以上解法一致
     *
     * 1,统计每一个字符最后出现的位置
     * 2,从头遍历字符，如果找到之前字符最大出现位置下标和当前下标相等，则找到了分割点
     *
     * 时间复杂度：O(N). 跟上述解法一样，都是遍历了两边字符串
     * 空间复杂度: 常数级别. 依然用了 长度为26 的 int数组
     * */
    public static List<Integer> partitionLabels1(String S) {

        List<Integer>list = new ArrayList<>();
        if (S == null || S.length() == 0) return list;

        int[] lasts = new int[26];
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            lasts[chars[i] - 'a'] = i;
        }

        int left = 0,right = 0;
        for (int i = 0; i < chars.length; i++) {
            count ++;
            right = Math.max(right, lasts[chars[i] - 'a']);
            if (i == right){
                list.add(right - left + 1);
                left = right + 1;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String string = "qazaqsxswsxzaqazswsxswqazswazxswaszxswazswazswazxswazxswazxswazswerfderfvcderfvcfdrfvcderfvcdferfvcderfvcdfrefvcderfvcfdrfcderfvcfdrfvcderfvcderfvcderfvcderftyhnbgtyhnbghtyhnbgtyhbngtyhnhgtyhbgtyhngyhnbgyhnbgtyhnikuikmjuikmjuikmjuikmjuikmjuikmjikmjuikmjiukmjuikmnjioploploploloplololoplolopl";
        List<Integer>list = partitionLabels(string);
        System.out.println(list);

        System.out.println(count);
    }
}
