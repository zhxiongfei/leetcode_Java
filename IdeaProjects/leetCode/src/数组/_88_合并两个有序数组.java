package 数组;

public class _88_合并两个有序数组 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int idx1 = m - 1;
        int idx2 = n - 1;

        while (idx1 >=0 || idx2 >= 0){

            if (idx2 < 0){
                break;
            }

            if (idx1 < 0){
                while (idx2 >= 0){
                    nums1[index --] = nums2[idx2 --];
                }
                continue;
            }

            if (nums1[idx1] > nums2[idx2]){
                nums1[index --] = nums1[idx1 --];
            }else {
                nums1[index--] = nums2[idx2--];
            }
        }
    }

   public static void main(String[] args) {

        int[] nums1 = {0};
        int[] nums2 = {1};
        merge(nums1,0,nums2,1);
   }
}
