package 位运算;

/*
给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

        示例 :

        输入: [1,2,1,3,2,5]
        输出: [3,5]
        注意：

        结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
        你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/single-number-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 如果使用额外的存储空间，这道题看起来非常简单
 * 但是题目中有说明，你的算法应该具有线性时间复杂度，和常数空间复杂度
 *
 * 使用额外的set， 符合线性时间复杂度，但是却不符合常数级的空间复杂度
 *
 * 如何将空间复杂度降到常数级别呢？
 * 可能又得利用，位运算
 * 而位运算，又是一个不懂的痛点
 * 所以就比较难受了
 * 争取早日将位运算拿下
 *
 * */
public class _260_只出现一次的数字III {

    public int[] singleNumber(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                set.remove(nums[i]);
            }else {
                set.add(nums[i]);
            }
        }

        Object[] tmp = set.toArray();
        int[] res = new int[2];
        for (int i = 0; i < tmp.length; i++) {
            res[i] = (int)tmp[i];
        }

        return res;
    }

    public int[] singleNumber1(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums) if ((num & diff) != 0) x ^= num;

        return new int[]{x, bitmask^x};
    }

}
