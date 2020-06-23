package 数组;

public class 合并两个有序数组 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int i = m-1, j = n-1, index = m + n - 1;
        while (i >= 0 || j >= 0){
            if (j < 0) break;
            if (i < 0) {
                while(j >= 0)
                    nums1[index --] = nums2[j --];
                break;
            }

            if (nums1[i] > nums2[j]){
                nums1[index --] = nums1[i--];
            }else {
                nums1[index --] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {

        int[] nums1 = {1,2,3,0,0,0,0,0,0};
        int[] nums2 = {2,5,6,7,9,10};

        merge(nums1,3,nums2,6);
    }
}
