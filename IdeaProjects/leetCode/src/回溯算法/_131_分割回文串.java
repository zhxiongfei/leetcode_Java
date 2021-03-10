package 回溯算法;

import java.util.*;

/**
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

        返回 s 所有可能的分割方案。

        示例:

        输入: "aab"
        输出:
        [
        ["aa","b"],
        ["a","a","b"]
        ]


        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-partitioning
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _131_分割回文串 {

    /**
     * 常规回溯算法
     * 因为每次都要计算子串是不是回文字串，每次都需要O(N)的时间
     * 提交后，算法通过了，但是用时长
     * 用时 227ms, 击败5%
     * */

    /**
     * 优化
     * 先进行一次O(N)的计算
     * 把 [i, j] 子串是否回文，记录下来，然后在判断是否是回文串时，只需要O(1)的时间就能查出来
     *
     * 计算 [i, j]字串四否回文，使用dp, 类似 5.最长回文字串
     *
     * 优化后 用时3ms, 击败67%
     * */

    /**
     * 动态规划的思想
     * 提前查出 [l, r]范围内的子串是不是回文字串
     *
     * dp三步走
     * 1，定义dp数组含义 dp[i][j]表示 s.subString(i, j)的子串是不是回文
     * 2，定义初始值 dp[0, 0] = false
     * 3，状态转移方程
     *    if(s[l] == s[r] && (dp[l + 1][r - 1] || r-l <= 2)) dp[i][j] = true
     *
     * */
    boolean[][] dp;
    public void getPalindrome(String s) {
        int len = s.length();
        dp = new boolean[len][len];

        for (int r = 0; r < s.length(); r++) {
            for (int l = 0; l <= r; l++) {
                if (r==l || (s.charAt(l) == s.charAt(r) && (dp[l+1][r-1] || r-l == 1))){
                    dp[l][r] = true;
                }
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0) return res;

        getPalindrome(s);
        Deque<String> path = new ArrayDeque<>();
        dfs(res,path,s, 0,0);
        return res;
    }

    private void dfs(List<List<String>> res, Deque<String> path, String s, int start, int depth){
        if (depth == s.length()){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) continue;

            String str = s.substring(start, i + 1);
            path.addLast(str);
            dfs(res, path, s, i + 1, depth + str.length());
            path.removeLast();
        }
    }

    private boolean isPalindrome(String s, int l, int r){
        return dp[l][r];
    }

    /**
     *
     * */
    private boolean isPalindrome(String s){
        if (s.length() == 0) return false;
        if (s.length() == 1) return true;
        int i = 0, j = s.length() - 1;
        while (i < j){
            if (s.charAt(i ++) == s.charAt(j --)) continue;
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _131_分割回文串 cls = new _131_分割回文串();
        String s = "aab";
        cls.partition(s);
    }
}
