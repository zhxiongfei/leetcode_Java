package 排序;

import java.util.Arrays;
import java.util.Comparator;

/**
给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
        注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。

         

        示例 1：

        输入：nums = [10,2]
        输出："210"
        示例 2：

        输入：nums = [3,30,34,5,9]
        输出："9534330"
        示例 3：

        输入：nums = [1]
        输出："1"
        示例 4：

        输入：nums = [10]
        输出："10"
         

        提示：

        1 <= nums.length <= 100
        0 <= nums[i] <= 109

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/largest-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _179_最大数 {

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return ((o1 + o2).compareTo(o2 + o1));
            }
        });
        if (strs[0].charAt(0) == '0') return "0";
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }

}
