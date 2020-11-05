package BFS;

import java.util.*;

/**
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

        每次转换只能改变一个字母。
        转换过程中的中间单词必须是字典中的单词。
        说明:

        如果不存在这样的转换序列，返回 0。
        所有单词具有相同的长度。
        所有单词只由小写字母组成。
        字典中不存在重复的单词。
        你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
        示例 1:

        输入:
        beginWord = "hit",
        endWord = "cog",
        wordList = ["hot","dot","dog","lot","log","cog"]

        输出: 5

        解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
        返回它的长度 5。
        示例 2:

        输入:
        beginWord = "hit"
        endWord = "cog"
        wordList = ["hot","dot","dog","lot","log"]

        输出: 0

        解释: endWord "cog" 不在字典中，所以无法进行转换。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/word-ladder
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _127_单词接龙 {

    /**
     * DFS超时
     * */
    int res = -1;
    List<String> resStrings = new ArrayList<>();
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Deque<String> path = new ArrayDeque<String>();
        boolean[] used = new boolean[wordList.size() + 1];
        dfs(path, beginWord, endWord, "", wordList, used);
        return res + 1;
    }

    private void dfs(Deque<String> path, String beginWord, String endWord, String curWord, List<String>wordList, boolean[] used){

        if (endWord.equals(curWord)) {
            resStrings.add(path.toString());
            res = (res == -1) ? path.size() : Math.min(res, path.size());
            System.out.println(res);
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            if (used[i]) continue;
            String str = wordList.get(i);
            if (!isLegal(beginWord, str)) continue;

            used[i] = true;
            path.addLast(str);
            dfs(path, str, endWord, str, wordList, used);
            used[i] = false;
            path.removeLast();
        }
    }
    private boolean isLegal(String beginWord, String curWord){
        int i = 0, diffCnt = 0;
        while (i < beginWord.length()){
            if (beginWord.charAt(i) == curWord.charAt(i)) {
                i ++;
                continue;
            }
            i ++;
            diffCnt ++;
            if (diffCnt > 1) return false;
        }
        return diffCnt == 1;
    }


    /**
     * BFS
     * */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 第一步: 先将 wordList 放到哈希表，便于判断某个单词是否在wordList里
        Set<String>wordSet = new HashSet<>(wordList);
        if (!wordList.contains(endWord)) return 0;
        wordSet.remove(beginWord);

        // 第二步: 图的广度优先遍历，必须使用队列和表示是否访问过的 visited 哈希表
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String>visited = new HashSet<>();
        visited.add(beginWord);

        // 第三步: 开始广度优先遍历，包含起点，因此初始化的时候步数是1
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 优先遍历当前队列中的单词
                String curWord = queue.poll();
                // 如果 currentWord 能够修改1个字符 与 endWord相同，则返回  step + 1
                if (changeWordEveryOneLetter(curWord, endWord, queue, visited,wordSet)){
                    return step + 1;
                }
            }
            step ++;
        }

        return 0;
    }

    private boolean changeWordEveryOneLetter(String curWord, String endWord, Queue<String>queue, Set<String>visited,Set<String>wordSet){
        char[] chars = curWord.toCharArray();
        for (int i = 0; i < endWord.length(); i++) {
            char oriChar = chars[i];
            for (char k = 'a'; k <= 'z'; k++){
                if (k == oriChar) continue;

                chars[i] = k;
                String nextWord = String.valueOf(chars);
                if (wordSet.contains(nextWord)){
                    if (nextWord.equals(endWord)) return true;
                    if (!visited.contains(nextWord)){
                        queue.add(nextWord);
                        // 注意: 添加到队列以后，必须马上标记为已经访问
                        visited.add(nextWord);
                    }
                }
            }
            chars[i] = oriChar;
        }
        return false;
    }

    public static void main(String[] args) {
        _127_单词接龙 cls = new _127_单词接龙();
        List<String>wordList = Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"});

        int res = cls.ladderLength("hit","cog",wordList);
        System.out.println(res);
    }
}
