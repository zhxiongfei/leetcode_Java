package 动态规划;

import java.util.*;

/**
一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：

        'A' -> 1
        'B' -> 2
        ...
        'Z' -> 26
        要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：

        "AAJF" ，将消息分组为 (1 1 10 6)
        "KJF" ，将消息分组为 (11 10 6)
        注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。

        给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。

        题目数据保证答案肯定是一个 32 位 的整数。

         

        示例 1：

        输入：s = "12"
        输出：2
        解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
        示例 2：

        输入：s = "226"
        输出：3
        解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
        示例 3：

        输入：s = "0"
        输出：0
        解释：没有字符映射到以 0 开头的数字。
        含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
        由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
        示例 4：

        输入：s = "06"
        输出：0
        解释："06" 不能映射到 "F" ，因为字符串含有前导 0（"6" 和 "06" 在映射中并不等价）。
         

        提示：

        1 <= s.length <= 100
        s 只包含数字，并且可能包含前导零。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/decode-ways
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _91_解码方法 {

    /**
     * 暴力回溯 + dfs
     * 超时
     * */
    public int numDecodings1(String s) {
        char[]chars = s.toCharArray();

        Deque<String> path = new ArrayDeque<>();
        List<List<String>>res = new ArrayList<>();
        dfs(s,path,res,s.length(),0,0);

        return res.size();
    }

    private void dfs(String s,Deque<String> path,List<List<String>>res,int length,int count,int startIdx){
        if (length == count){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int end = startIdx + 1; (end <= length && end-startIdx<=2); end++) {
            //  以0开头，剪枝
            if (s.charAt(startIdx) == '0') return;
            String subStr = s.substring(startIdx, end);

            // >26，剪枝
            if (Integer.valueOf(subStr) > 26) continue;

            count += (end-startIdx);
            path.add(subStr);
            dfs(s,path,res,length,count,end);
            count -= (end-startIdx);
            path.removeLast();
        }
    }

    /**
     * 动态规划
        有个思路很直观 : 说这个题就像是有条件的斐波那契数列
        dp[i] = (如果 c 能单独作为字母)dp[i-1] + (如果 c 和前一个位置能拼成字母)dp[i-2];
        一道字符串类的动态规划题
        不难发现对于字符串 s 的某个位置 i 而言
            我们只关心「位置 i 自己能否形成独立 item 」和「位置 i 能够与上一位置（i-1）能否形成 item
            而不关心 i-1 之前的位置
     * */
    public int numDecodings(String s) {
        int length = s.length();
        if (length == 0) return 0;
        if (length == 1) return s.charAt(0) == '0' ? 0 : 1;

        int[]dp = new int[length+1];
        dp[0] = 1;
        s = " " + s;
        char[]chars = s.toCharArray();
        for (int i = 1; i <= length; i++) {
            char c = chars[i];

            // a 代表 c 单独作为一个字母
            int a = c - '0';
            // b 代表 c 和前一个位置的字母拼成的字母
            int b = (chars[i-1] - '0') * 10 + a;

            // c不能作为字母。 并且 c 和 前一个位置拼，也不能作为字母 -> 默认就是 dp[i] = 0

            // 能由 c, 单独作为 一个字母
            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (a >= 1 && a <= 9) dp[i] = dp[i-1];

            // 能和前一个位置拼，作为一个字母
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (b >= 10 && b <= 26) dp[i] += dp[i-2];
        }
        return dp[length];
    }

    public static void main(String[] args) {
        _91_解码方法 cls = new _91_解码方法();
        System.out.println(cls.numDecodings("271873"));
    }
}
