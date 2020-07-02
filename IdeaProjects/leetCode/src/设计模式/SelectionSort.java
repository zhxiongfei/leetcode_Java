package 设计模式;

public class SelectionSort<T> {

//    // 交换元素
//    public void swap(Comparable[] nums, int i1, int i2){
//        Comparable tmp = nums[i1];
//        nums[i1] = nums[i2];
//        nums[i2] = tmp;
//    }
//
//    // 选择排序
//    public void selectionSort(Comparable[] nums){
//        for (int i = nums.length - 1; i > 0; i--){
//            int maxIdx = 0;
//            for (int j = 1; j <= i; j++) {
//                if (nums[j].compareTo(nums[maxIdx]) > 0){
//                    maxIdx = j;
//                }
//            }
//            swap(nums,maxIdx, i);
//        }
//    }

    // 交换元素
    public void swap(T[] nums, int i1, int i2){
        T tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

    // 选择排序
    public void selectionSort(T[] nums, Comparator<T> comparator){
        for (int i = nums.length - 1; i > 0; i--){
            int maxIdx = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(nums[j], nums[maxIdx]) > 0){
                    maxIdx = j;
                }
            }
            swap(nums,maxIdx, i);
        }
    }


    public static void main(String[] args) {

//        SelectionSort sort = new SelectionSort();
//        Dog[] dogs = {new Dog(98),new Dog(56),new Dog(8),
//                new Dog(12),new Dog(15),new Dog(18),
//                new Dog(13),new Dog(16),new Dog(24),
//                new Dog(14),new Dog(17),new Dog(873)};
//
//        sort.selectionSort(dogs);

//        for (int i = 0; i < dogs.length; i++) {
//            System.out.println(dogs[i]);
//        }

        SelectionSort sort = new SelectionSort();
        Cat[] cats = {new Cat(39,1),new Cat(20,4), new Cat(10,2)};
        sort.selectionSort(cats, new Comparator() {
            @Override
            public int compare(Cat o1, Cat o2) {
                return o1.age - o2.age;
            }
        });

        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i]);
        }

    }

}
