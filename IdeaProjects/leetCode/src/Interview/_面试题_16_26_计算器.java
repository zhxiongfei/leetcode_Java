package Interview;

/*
给定一个包含正整数、加(+)、减(-)、乘(*)、除(/)的算数表达式(括号除外)，计算其结果。

        表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。

        示例 1:

        输入: "3+2*2"
        输出: 7
        示例 2:

        输入: " 3/2 "
        输出: 1
        示例 3:

        输入: " 3+5 / 2 "
        输出: 5
        说明：

        你可以假设所给定的表达式都是有效的。
        请不要使用内置的库函数 eval。


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/calculator-lcci
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class _面试题_16_26_计算器 {
    public static int calculate(String s) {
        if(s==null||s.length()==0)return 0;
        s=s.replace(" ","");
        Stack<Integer> num=new Stack<>();
        int n=0;
        char op='+';
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(Character.isDigit(c)){
                n=n*10+(c-'0');
            }
            if(!Character.isDigit(c)||i==s.length()-1){
                int pre;
                switch(op){
                    case '+': num.push(n);
                        break;
                    case '-': num.push(-n);
                        break;
                    case '*': pre=num.pop();
                        num.push(pre*n);
                        break;
                    case '/': pre=num.pop();
                        num.push(pre/n);
                        break;
                }
                op=c;
                n=0;
            }
        }
        int res=0;
        while(!num.isEmpty()){
            res+=num.pop();
        }
        return res;
    }

    public static void main(String[] args) {
        calculate("111*56-1+1");
    }
}
