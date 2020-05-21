package 排序;

/*
* 插入排序基础上做优化
*
* 将交换改为插入
* 当依次比较时，没必要每次都交换
* 可以先把 当前元素，挪动到它前一个元素
* 交换完毕后，当前元素赋值为交换的末尾元素
*
* */
public class InsertionSort1 extends Sort{

    @Override
    protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int cur = begain;
            // 保存当前遍历的值
            int element = array[cur];
            while (cur > 0 && cmpElement(element, array[cur - 1]) < 0){
                // 当前元素比 上一个元素小时，用前一个元素的值覆盖当前值
                array[cur] = array[cur - 1];
                cur --;
            }
            // 最后把最后一次遍历的index 设置为 之前保存的节点值，
            array[cur] = element;
        }
    }
}
