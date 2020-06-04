package 剑指offer;

public class _面试题39_数组中出现次数超过一半的数字 {

    /*
    *
    *
    * 摩尔投票算法
    *
    * 多种解法及解析可见
    * https://zhangxiongfeiv.github.io/post/169多数元素与摩尔投票算法/
    *
    * */
    public int majorityElement(int[] nums) {
        int count = 1;
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != tmp){
                count --;
                if (count < 0){
                    count = 1;
                    tmp = nums[i];
                }
            }else {
                count ++;
            }
        }

        return tmp;
    }
}
