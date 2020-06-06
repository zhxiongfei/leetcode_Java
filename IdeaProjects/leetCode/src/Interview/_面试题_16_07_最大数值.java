package Interview;

/*
编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。

        示例：

        输入： a = 1, b = 2
        输出： 2
 */

/**
 *
 * 等整理了有关位运算相关的知识再整理
 *
 * */
public class _面试题_16_07_最大数值 {

    public int maximum(int a, int b) {
        long p = a , q = b;
        return (int)((p + q + Math.abs(p-q))/2);
    }
}
