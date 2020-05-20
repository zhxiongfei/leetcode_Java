package 排序;

public abstract class Sort {

    protected Integer[] array;

    public void sort(Integer[] array){
        if (array == null || array.length < 2) return;

        this.array = array;
        sort();
    }

    /*
    * 交换，array i 和 j位置的元素
    * */
    protected void swap(int i1, int i2){
        int tmp = array[i1];
        array[i1] = array[i2];
        array[i2] = tmp;
    }

    /*
    * 返回值等于0 , 代表array[i1] == array[i2]
    * 返回值 < 0,  代表array[i1] < array[i2]
    * 返回值 > 0,  代表array[i1] > array[i2]
    * */
    protected int cmp(int i1, int i2){
        return array[i1] - array[i2];
    }

    protected abstract void sort();
}
