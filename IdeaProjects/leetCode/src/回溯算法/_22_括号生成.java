package 回溯算法;

import java.util.*;

/**
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

         

        示例：

        输入：n = 3
        输出：[
        "((()))",
        "(()())",
        "(())()",
        "()(())",
        "()()()"
        ]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/generate-parentheses
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _22_括号生成 {

    /**
     * 回溯
     * 理不清楚思路时，可以在纸上把递归树，以及在哪里剪枝画出来
     * 之后就是套模板，写剪枝条件了
     * */
    public List<String> generateParenthesis(int n) {
        List<String>res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, 0, n, 0, 0);
        return res;
    }

    /**
     * 回溯主函数
     * @param res       最终的结果数组
     * @param path      当前路径
     * @param depth     当前深度
     * @param n         括号对数
     * @param leftCnt   左括号个数
     * @param rightCnt  右括号个数
     */
    public void dfs(List<String> res, StringBuilder path,int depth, int n, int leftCnt, int rightCnt){
        if (depth == 2*n){
            res.add(new String(path.toString()));
            return;
        }

        int i = 0;
        while (i < 2){
            boolean flag = i % 2 == 0 ? true : false;
            i ++;

            // 剪枝
            // 情况1 : 左右括号处于平衡状态，新添加右括号
            if (!flag && leftCnt == rightCnt) continue;
            // 情况2 : 左括号已经到最大，并且新添加到仍然是左括号
            if (flag && leftCnt >= n) continue;
            // 情况3 : 右括号已经到最大，并且新添加仍然是右括号
            if (!flag && rightCnt >= n) continue;

            char c = flag ? '(' : ')';
            path.append(c);

            dfs(res,path,depth + 1, n, leftCnt + (flag ? 1 : 0), rightCnt + (!flag ? 1 : 0));

            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        _22_括号生成 cls = new _22_括号生成();
        cls.generateParenthesis(3);
    }
}
