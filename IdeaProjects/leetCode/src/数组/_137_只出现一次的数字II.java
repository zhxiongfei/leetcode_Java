package 数组;

import java.util.HashMap;

/*
给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。

        说明：

        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

        示例 1:

        输入: [2,2,3,2]
        输出: 3
        示例 2:

        输入: [0,1,0,1,0,1,99]
        输出: 99

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/single-number-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 */
public class _137_只出现一次的数字II {

    /**
     *
     * map记录 <num, count>
     *
     * 遍历map value为1时，return key
     *
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     *
     * 提交后，算法虽然 ac 了。 但是耗时特别长。
     * 耗时进 1600ms. 在所有提交中，位于倒数 5%
     *
     * 而且，本题重点考察的是，如何用常数时间复杂度解决问题，而非用额外的 O(N)存储空间
     *
     * */
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],map.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < map.keySet().toArray().length; i++) {
            int num = map.get(map.keySet().toArray()[i]);
            if (num == 1) return (int) map.keySet().toArray()[i];
        }

        return -1;
    }

    /**
     *
     *
     * 位运算
     *
     * 真的是该，系统的学习一遍位运算
     * 但是，首先得把组原学习一遍， 感觉很困难
     * 位运算还是一个 痛点
     *
     * */
    public int singleNumber1(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }

}
