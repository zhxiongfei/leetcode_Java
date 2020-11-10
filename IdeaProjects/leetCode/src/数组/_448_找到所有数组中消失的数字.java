package 数组;

import java.util.ArrayList;
import java.util.List;

public class _448_找到所有数组中消失的数字 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1){
                if (nums[i] == -1) break;
                if (nums[i] == nums[nums[i] - 1]){
                    nums[i] = -1;
                    break;
                }
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1){
                res.add(i + 1);
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
