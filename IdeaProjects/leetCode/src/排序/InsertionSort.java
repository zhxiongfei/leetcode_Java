package 排序;

/*
* 从第一位开始，依次与之前元素比较
* 当比之前元素小时，交换
* 挨个交换直到 末尾元素
* 最差时间复杂度 : O(N ^ 2)
* 平均时间复杂度 : O(N * log N)
* 最优时间复杂度 : O(N)
* 空间复杂度度 : O(1)
*
* 与逆序对的数量成正比
* */
public class InsertionSort extends Sort{

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int cur = begain;
            while (cur > 0 && cmp(cur, cur - 1) < 0){
                swap(cur, cur - 1);
                cur --;
            }
        }
    }
}
