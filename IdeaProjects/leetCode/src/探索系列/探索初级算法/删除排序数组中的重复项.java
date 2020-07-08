package 探索系列.探索初级算法;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class 删除排序数组中的重复项 {

    public int removeDuplicates(int[] nums) {

        /**
         *
         * 快慢指针
         * 慢指针指向不重复元素之外的第一个
         * 快指针遍历元素
         * 当慢指针指向的元素 和 快指针指向的元素 不相等时
         * 则 慢指针的下一个 赋值为 快指针指向的元素
         *
         * 最终慢指针指向的是 不重复数组的元素的下标。 所以个数为 慢指针 + 1
         *
         * */
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j])
                nums[++i] = nums[j];
        }

        return i + 1;
    }

    public static void main(String[] args) {

        String s1 = "Hello";
        String s2 = "Hello";

        String s3 = new String("Hello");
        String s4 = new String("Hello");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println(s1 == s3.intern());
    }
}
