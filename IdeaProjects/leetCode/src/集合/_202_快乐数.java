package 集合;

/*
编写一个算法来判断一个数是不是“快乐数”。

        一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。

        示例: 

        输入: 19
        输出: true
        解释:
        1^2 + 9^2 = 82
        8^2 + 2^2 = 68
        6^2 + 8^2 = 100
        1^2 + 0^2 + 02 = 1

        38

        73

        58

        89

        145

        42

        20

        40

        160

        37

        59


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/happy-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import com.sun.source.util.Trees;

import java.time.temporal.Temporal;
import java.util.*;

public class _202_快乐数 {

    // set记录
    public static boolean isHappy1(int n) {

        Set<Integer> s = new HashSet<>();
        s.add(n);

        int res = n;
        while (true){
            String aStr = String.valueOf(res);
            char[] chars = aStr.toCharArray();

            res = 0;
            for (int i = 0; i < chars.length; i++) {
                int num = chars[i] - '0';
                res += Math.pow(num,2);
            }

            if (res == 1) return true;
            if (s.contains(res)) return false;
            if (s.add(res));
        }
    }

    public static boolean s(int n){
        Set<Integer> set = new HashSet<>();
        while (n != 1 && !set.contains(n)){
            set.add(n);
            n = nextNum(n);
        }

        return n == 1;
    }

    // 方法：使用“快慢指针”思想找出循环：“快指针”每次走两步，“慢指针”每次走一步，当二者相等时，即为一个循环周期。此时，判断是不是因为1引起的循环，是的话就是快乐数，否则不是快乐数。
    // 注意：此题不建议用集合记录每次的计算结果来判断是否进入循环，因为这个集合可能大到无法存储；另外，也不建议使用递归，同理，如果递归层次较深，会直接导致调用栈崩溃。不要因为这个题目给出的整数是int型而投机取巧。
    public static boolean isHappy(int n) {

        int slow = n;
        int fast = nextNum(n);

        while (slow != fast){
            slow = nextNum(slow);
            fast = nextNum(nextNum(fast));
        }

        return slow == 1;
    }

    public static int nextNum(int n){
        int res = 0;
        while (n > 0){
            int bit = n % 10;
            res += Math.pow(bit,2);
            n = n / 10;
        }

        return res;
    }

    public static void main(String[] args){
        boolean res = isHappy(2);
        System.out.println(res);
    }

    public int evalRPN(String[] tokens){

        final HashSet<String> symbols = new HashSet<String>(){{
            add("+");
            add("-");
            add("*");
            add("/");
        }};

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if (symbols.contains(s)){
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());

                int res = 0;
                if (s.equals("+")){
                    res = num2 + num1;
                }else if (s.equals("-")){
                    res = num2 - num1;
                }else if (s.equals("*")){
                    res = num2 * num1;
                }else {
                    res = num2 / num1;
                }

                stack.push(String.valueOf(res));
            }else{
                stack.push(s);
            }
        }

        return Integer.parseInt(stack.peek());
    }
}
