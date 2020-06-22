package 哈希表;

import java.util.HashMap;
import java.util.Map;

public class _219_存在重复元素II {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.keySet().contains(nums[i])){
                int startIdx = map.get(nums[i]);
                if (i - startIdx <= k) return true;

                map.put(nums[i], i);
            }
            map.put(nums[i], i);
        }

        return false;
    }

}
