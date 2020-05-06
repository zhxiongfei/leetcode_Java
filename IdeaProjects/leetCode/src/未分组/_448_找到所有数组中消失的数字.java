package 未分组;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class _448_找到所有数组中消失的数字 {

    static void swap(int[] nums, int i1,int i2){

        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    static List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != i-1){
                swap(nums,i,num - 1);
                i --;
            }
        }

        System.out.println(1);
        return null;
    }

    public static void main(String[] args){

        String[] strs = {"flower","flag","flolow"};
        System.out.println(longestCommonPrefix(strs));
    }

    static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if (strs == null || strs.length == 0) return sb.toString();
        if (strs.length == 1) return strs[0];

        int cnt = strs[0].length();
        for(int i=1; i< strs.length; i++){
            cnt = Math.min(cnt,strs[i].length());
        }

        // 外层循环-第i个字母
        for(int i=0; i< cnt; i++){
            char c = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                char c1 = strs[j].charAt(i);
                if(c != c1) return sb.toString();

                if (j == strs.length - 1) sb = sb.append(c1);
            }
        }

        return sb.toString();
    }
}
