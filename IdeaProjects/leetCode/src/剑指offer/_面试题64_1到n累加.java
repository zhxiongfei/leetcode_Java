package 剑指offer;

/*
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

         

        示例 1：

        输入: n = 3
        输出: 6
        示例 2：

        输入: n = 9
        输出: 45
         

        限制：

        1 <= n <= 10000

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/qiu-12n-lcof
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class _面试题64_1到n累加 {

    // 思路一
    // 等差数列求和
    // 时间复杂度 O(1)
    // 空间复杂度 O(1)
    // 但是题目说明不让使用 以下操作
    // 乘除法、for、while、if、else、switch、case
    public int sumNums(int n) {
        return (n * (1 + n)) >> 1;
    }


    // 思路二
    // 不使用以上操作的方法
    // 想到递归
    // 时间复杂度 O(n)
    // 空间复杂度 O(n) 递归调用栈n层
    int count = 0;
    public int sumNums1(int n) {
        if(n == 1) return 1;
        count ++;
        return n + sumNums1(n - 1);
    }
    
    // 思路三
    // 需要位运算
    // 然而对位运算 不熟悉
    // 所以下一步，学习位运算
    // 整理关于位运算的常用方法以及题目
    // 学习完有关位运算只是，再回来看这道题
    public static void main(String[] args) {
        _面试题64_1到n累加 cls = new _面试题64_1到n累加();
        cls.sumNums1(5000);

        System.out.println(cls.count);
    }
}
