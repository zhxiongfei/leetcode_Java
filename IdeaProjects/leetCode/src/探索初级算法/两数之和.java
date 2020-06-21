package 探索初级算法;

import java.util.HashMap;

public class 两数之和 {

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])){
                int [] res = {map.get(nums[i]),i};
                return res;
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

}
