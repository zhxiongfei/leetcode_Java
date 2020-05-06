package 其他.模拟面试1;

/*
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

        示例 1:

        输入: 123
        输出: 321
        示例 2:

        输入: -123
        输出: -321
        示例 3:

        输入: 120
        输出: 21
 */

import java.io.FileNotFoundException;
import java.util.Stack;

public class _反转整数 {

    public int reverse(int x) {

        if (x == 0) return 0;
        boolean isNegative = false;
        if (x < 0){
            isNegative = true;
        }

        Stack s = new Stack();

        String str = String.valueOf(x);
        int start = isNegative ? 1 : 0;
        for (int i = start; i < str.length(); i++) {
            s.push(str.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()){
            sb.append(s.pop());
        }

        if (isNegative){
            sb.insert(0,'-');
        }

        try {
            return Integer.parseInt(sb.toString());
        }catch (Exception e){
            return 0;
        }
    }
}
