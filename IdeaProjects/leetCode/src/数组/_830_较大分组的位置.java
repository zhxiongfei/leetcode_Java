package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
        例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。\
        分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
        我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
        找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。

        示例 1：
        输入：s = "abbxxxxzzy"
        输出：[[3,6]]
        解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
        示例 2：

        输入：s = "abc"
        输出：[]
        解释："a","b" 和 "c" 均不是符合要求的较大分组。
        示例 3：

        输入：s = "abcdddeeeeaabbbcd"
        输出：[[3,5],[6,9],[12,14]]
        解释：较大分组为 "ddd", "eeee" 和 "bbb"
        示例 4：

        输入：s = "aba"
        输出：[]
         
        提示：
        1 <= s.length <= 1000
        s 仅含小写英文字母
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/positions-of-large-groups
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _830_较大分组的位置 {

    /**
     * 双指针
     * 一次遍历
     * 时间复杂度 : O(n)
     * 空间复杂度 : O(1)
     * */
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s == null || s.length() <= 2) return res;

        char[] chars = s.toCharArray();
        int left = 0, right = 1, cnt = 1;
        while (right < chars.length){
            char pre = chars[left];
            char cur = chars[right];

            if (pre == cur){
                right ++;
                cnt ++;
            }else {
                if (cnt >= 3) res.add(Arrays.asList(left, right - 1));
                pre = cur;
                cnt = 1;
                left = right++;
            }
        }
        if (cnt >= 3) res.add(Arrays.asList(left, right - 1));
        return res;
    }

    public static void main(String[] args) {
        _830_较大分组的位置 cls = new _830_较大分组的位置();
        List<List<Integer>>res = cls.largeGroupPositions("aaa");
        System.out.println(res);
    }
}
