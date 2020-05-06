package 分治;

/*
给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。

        你可以假设数组是非空的，并且给定的数组总是存在多数元素。

        示例 1:

        输入: [3,2,3]
        输出: 3
        示例 2:

        输入: [2,2,1,1,1,2,2]
        输出: 2

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/majority-element
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Arrays;
import java.util.HashMap;

public class _169_多数元素 {

    // map记录 时间复杂度O(n) 空间复杂度O(n)
    public int majorityElement1(int[] nums) {
        if (nums.length <= 2) return nums[0];

        int half = nums.length >> 1;
        HashMap<Integer,Integer> counts= new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (!counts.containsKey(num)){
                counts.put(num,1);
                continue;
            }
            int cnt = counts.get(num);
            cnt ++;
            if (cnt > half){
                return num;
            }
            counts.put(num,cnt);
        }
        return -1;
    }

    // 先排序，占据数组最中间index的元素，肯定是多数元素
    public int majorityElement2(int[] nums) {

        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    // 分治
    public int majorityElement(int[] nums) {

        return majority(nums,0,nums.length);
    }

    public int majority(int[] nums,int begain,int end){

        if (end - begain < 2) return nums[begain];

        int mid = (begain + end) >> 1;

        int left = majority(nums,begain,mid);
        int right = majority(nums,mid,end);

        // 左右子数组众数一样， 则其定为完整数组众数
        if (left == right){
            return left;
        }

        // 左右子数组众数不一样，则分别计算左 右众数次数。 返回多者。
        int leftCnt = countInRange(nums,left,begain,end);
        int rightCnt = countInRange(nums,right,begain,end);

        return rightCnt > leftCnt ? right : left;
    }

    public int countInRange(int[] nums,int num, int begain,int end){
        int count = 0;
        for (int i = begain; i < end; i++) {
            if (nums[i] == num){
                count ++;
            }
        }
        return count;
    }
}
