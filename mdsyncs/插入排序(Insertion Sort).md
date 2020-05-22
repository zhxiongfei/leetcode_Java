#### 插入排序(Insertion Sort)



##### 执行流程

- 从第一位开始, 依次与之前元素比较
- 当比之前元素小时, 交换
- 挨个交换直到 末尾元素
- 最差时间复杂度 : O(N ^ 2)
- 平均时间复杂度 : O(N * log N)
- 最优时间复杂度 : O(N)
- 空间复杂度 : O(1)



###### 实现:

代码如下：

```java
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

		protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int cur = begain;
            while (cur > 0 && cmp(cur, cur - 1) < 0){
                swap(cur, cur - 1);
                cur --;
            }
        }
    }
```



###### 优化一：

- 把交换改为插入 (从swap (三次操作), 变为 挪动(一次操作))
- 先把当前元素，挪动到它前一个元素的位置
- 交换完毕后, 当前元素的位置赋值为交换的末尾元素

```java
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
```



###### 优化二：

- 在插入排序时，我们发现当前元素前边的元素已经有序
- 所以在找插入位置时，我们使用二分搜索寻找插入顺序
- 用 logN 的时间复杂度, 就能找到插入位置

```java
protected void sort() {
        for (int begain = 1; begain < array.length; begain++) {
            int element = array[begain];

            // 插入的位置
            int index = binarySearch(begain);

            // 在 index 和 begain之间的元素都向后挪动一位
            for (int i = begain; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = element;
        }
    }

    private int binarySearch(int index){
        int begain = 0;
        int end = index;
        while (begain < end){
            int mid = (begain + end) >> 1;
            if (array[mid] < array[index]){
                begain = mid + 1;
            }else {
                end = mid;
            }
        }
        return begain;
    }
```

