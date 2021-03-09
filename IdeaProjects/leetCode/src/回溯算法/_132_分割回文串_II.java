package 回溯算法;

import java.util.*;

/**
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

        返回符合要求的 最少分割次数 。
        示例 1：
        输入：s = "aab"
        输出：1
        解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
        示例 2：

        输入：s = "a"
        输出：0
        示例 3：

        输入：s = "ab"
        输出：1
         

        提示：

        1 <= s.length <= 2000
        s 仅由小写英文字母组成

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _132_分割回文串_II {

    /**
     * 根据 131 动态规划 + 贪心解法
     * 时间复杂度 : O(N * 2^N)
     * 空间复杂度 : O(N)
     *
     * 因为测试用例字符串长达 2000
     * 提交后超时了
     * */

    int res = Integer.MAX_VALUE;
    public int minCut1(String s) {
        getPalindrome(s);
        List<Integer>list = new ArrayList<>();
        Deque<String>path = new ArrayDeque<>();
        dfs(path, s, 0, 0, 0);

        return res;
    }

    private void dfs(Deque<String>path,String s,int start, int depth, int step){
        if (depth == s.length()){
            res = Math.min(res, step - 1);
            return;
        }
        for (int i = start; i < s.length(); i++){
            if (!dp[start][i]) continue;

            String str = s.substring(start, i + 1);
            path.addLast(str);
            dfs(path,s, i + 1, depth + str.length(), step + 1);
            path.removeLast();
        }
    }

    boolean[][]dp;
    public void getPalindrome(String s){
        int len = s.length();
        dp = new boolean[len][len];
        for (int r = 0; r < len; r ++){
            for (int l = 0; l <= r; l++){
                if (l==r || (s.charAt(l) == s.charAt(r) && (dp[l+1][r-1] || r-l == 1))){
                    dp[l][r] = true;
                }
            }
        }
    }

    /**
     *
     * 此题目与 300.最长上升子序列类似
     *
     * 假设 f[i] 表示字符串前缀 s[0..i]的最小分割次数。
     * 要想得到 f[i]的值，可以考虑枚举 s[0..i]分割初的最后一个回文串
     * f[i] = min(f[j]) + 1; 其中 0 <= j < i, 并且 s[j + 1, i] 是回文串
     *
     * 即我们枚举最后一个回文串的起始位置 j + 1,  保证 s[j + 1, i] 是一个回文串。 那么 f[i]就可以从 f[j] + 1，而来.
     *
     * 还有一种情况， 如果 s[0..i]本身是会问串， 则 f[i] = 0;
     *
     * 此外，需要构建 dp 预处理数组， 与 题目131类似。
     *
     * 整体时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(N ^ 2)
     * */
    public int minCut(String s) {
        getPalindrome(s);
        int length = s.length();
        int[] cuts = new int[length];
        Arrays.fill(cuts, Integer.MAX_VALUE);
        for (int r = 0; r < length; r++) {
            if (dp[0][r]){
                cuts[r] = 0;
            }else {
                for (int l = 0; l < r; l++){
                    if (dp[l+1][r]){
                        cuts[r] = Math.min(cuts[l] + 1, cuts[r]);
                    }
                }
            }
        }
        return cuts[length - 1];
    }

    public static void main(String[] args) {
        _132_分割回文串_II cls = new _132_分割回文串_II();
        System.out.println(cls.minCut("aab"));
    }

}
