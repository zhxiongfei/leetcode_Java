package 位运算;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class 只出现一次的数字 {

    // set存储
    public static int singleNumber(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                set.remove(nums[i]);
            else
                set.add(nums[i]);
        }

        Object[] array = set.toArray();
        return (int)array[0];
    }

    // 位运算
    public int singleNumbe1r(int[] nums) {

        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {

        int[] nums = {2,2,1};
        singleNumber(nums);
    }

}
