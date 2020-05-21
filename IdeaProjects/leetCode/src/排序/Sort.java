package 排序;

import java.text.DecimalFormat;
import java.util.Comparator;

public abstract class Sort {

    protected Integer[] array;
    private int cmpcount;
    private int swapcount;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    public void sort(Integer[] array){
        if (array == null || array.length < 2) return;

        this.array = array;
        long begain = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - begain;
    }

    /*
    * 交换，array i 和 j位置的元素
    * */
    protected void swap(int i1, int i2){
        swapcount ++;

        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /*
    * 比较两个下标的元素
    * 返回值等于0 , 代表array[i1] == array[i2]
    * 返回值 < 0,  代表array[i1] < array[i2]
    * 返回值 > 0,  代表array[i1] > array[i2]
    * */
    protected int cmp(int i1, int i2){
        cmpcount ++;
        return array[i1] - array[i2];
    }

    /*
    * 比较两个元素
    * 返回值 == 0, 代表相等
    *        < 0, 代表 v1 < v2
    *        > 0, 代表 v1 > v2
    * */
    protected int cmpElement(int v1, int v2){
        cmpcount ++;
        return v1 - v2;
    }

    protected abstract void sort();

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpcount);
        String swapCountStr = "交换：" + numberString(swapcount);
        return "【" + getClass().getSimpleName() + "】\n"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }
}
