package 字符串;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。

        若可行，输出任意可行的结果。若不可行，返回空字符串。

        示例 1:

        输入: S = "aab"
        输出: "aba"
        示例 2:

        输入: S = "aaab"
        输出: ""
        注意:

        S 只包含小写字母并且长度在[1, 500]区间内。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reorganize-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _767_重构字符串 {

    /**
     * 第一步 : 记每个字母出现的次数, 因为只包含小写字母,所以用 长度为 26 的int数组记录出现次数
     * 第二步 : 维护最大堆存储字母，堆顶元素为出现次数最多的字母。遍历 'a' - 'z' 加入最大堆
     * 第三步 : 当堆顶元素 > 1时, 取出堆顶的两个元素，拼接到重构的字符串，两个元素 次数 - 1。重新入堆。
     *         循环以上，直到堆中元素 <= 1. 若 堆中元素 > 0, 拼接最后一个字母
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(26) = O(1)
     * */
    public String reorganizeString(String S) {
        if (S == null || S.length() < 1) return S;
        int maxCnt = 0, length = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray()) {
            counts[c - 'a'] ++;
            maxCnt = Math.max(maxCnt, counts[c - 'a']);
        }
        if (maxCnt > ((length + 1) >> 1)) return "";

        // 使用最大堆
        PriorityQueue<Character> queue = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return counts[o2 - 'a'] - counts[o1 - 'a'];
            }
        });

        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) queue.offer(c);
        }

        StringBuilder sb = new StringBuilder();
        while (queue.size() > 1){
            char c1 = queue.poll();
            char c2 = queue.poll();
            sb.append(c1);
            sb.append(c2);
            counts[c1 - 'a'] --;
            counts[c2 - 'a'] --;
            if (counts[c1 - 'a'] > 0) queue.offer(c1);
            if (counts[c2 - 'a'] > 0) queue.offer(c2);
        }
        if (queue.size() > 0) sb.append(queue.poll());
        return sb.toString();
    }

    public String reorganizeString1(String S) {
        if (S == null || S.length() < 1) return S;
        int maxCnt = 0, length = S.length();
        int[] counts = new int[26];
        for (char c : S.toCharArray()) {
            counts[c - 'a'] ++;
            maxCnt = Math.max(maxCnt, counts[c - 'a']);
        }
        if (maxCnt > ((length + 1) >> 1)) return "";

        char[] res = new char[length];
        int evenIdx = 0, oddIdx = 1;
        int halfLen = length / 2;
        for (int i = 0; i < 26; i++) {
            char c = (char)('a' + i);
            while (counts[i] > 0 && counts[i] <= halfLen && oddIdx < length){
                res[oddIdx] = c;
                counts[i] --;
                oddIdx += 2;
            }
            while (counts[i] > 0){
                res[evenIdx] = c;
                counts[i] --;
                evenIdx += 2;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        _767_重构字符串 cls = new _767_重构字符串();
        cls.reorganizeString("zhmyo");
    }
}
