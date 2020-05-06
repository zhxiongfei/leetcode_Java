package 集合;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.TreeSet;

public class _349_两个数组的交集 {
    public int[] intersection(int[] nums1, int[] nums2) {

//        此题就是一道典型的考察集合使用的一道题
//        我们只需要把一个数组中的元素全部放入set中
//        然后再遍历另一个数组，遍历的值如果能在set中找到，那么就说明该值是交集
//        此时我们就应该放入另一个set中（去重，我们不想最后的数组中有多个相同的值）。最后再把set转化为vector即可

        TreeSet set = new TreeSet();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        TreeSet resSet = new TreeSet();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])){
                resSet.add(nums2[i]);
            }
        }

        int[] res = new int[resSet.size()];
        Iterator iterator = resSet.iterator();
        int k = 0;
        while (iterator.hasNext()) {
            res[k] = (int) iterator.next();
            k++;
        }

        return res;
    }
}
