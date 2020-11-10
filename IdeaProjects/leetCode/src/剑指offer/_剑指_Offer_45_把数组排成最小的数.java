package 剑指offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

        示例 1:

        输入: [10,2]
        输出: "102"
        示例 2:

        输入: [3,30,34,5,9]
        输出: "3033459"
         

        提示:

        0 < nums.length <= 100
        说明:

        输出结果可能非常大，所以你需要返回一个字符串而不是整数
        拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _剑指_Offer_45_把数组排成最小的数 {

    public String minNumber(int[] nums) {

        List<String>list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o1 + o2).compareTo(o2 + o1);
            }
        });
        return String.join("",list);
    }

}
