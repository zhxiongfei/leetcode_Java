package BFS;

import java.util.*;

/**
一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。
        假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。

        例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。

        与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。

        现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变化次数。如果无法实现目标变化，请返回 -1。

        注意:

        起始基因序列默认是合法的，但是它并不一定会出现在基因库中。
        所有的目标基因序列必须是合法的。
        假定起始基因序列与目标基因序列是不一样的。
        示例 1:

        start: "AACCGGTT"
        end:   "AACCGGTA"
        bank: ["AACCGGTA"]

        返回值: 1
        示例 2:

        start: "AACCGGTT"
        end:   "AAACGGTA"
        bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

        返回值: 2
        示例 3:

        start: "AAAAACCC"
        end:   "AACCCCCC"
        bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

        返回值: 3

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/minimum-genetic-mutation
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _433_最小基因变化 {

    /**
     * 与 127.单词接龙基本完全一样
     * */
    public int minMutation(String start, String end, String[] bank) {
        // 第1步: 先将 bank 放到哈希表，便于判断某个基因在 bank 中
        Set<String>bankSet = new HashSet<String>(Arrays.asList(bank));
        if (!bankSet.contains(end)) return -1;

        char[] genes = {'A','C','G','T'};

        // 第2步: 图的广度优先遍历，使用队列和表示是否访问过的 visited 哈希表
        Queue<String>queue = new LinkedList<>();
        queue.offer(start);

        Set<String>visited = new HashSet<>();
        visited.add(start);

        // 第3步: 开始广度优先遍历，包括起点，因此的时候步数是1
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 优先遍历当前队列中的单词
                String curGene = queue.poll();
                // 如果 curGene 能够修改1个 字符，和 end相同， 则返回 step + 1
                if (changeGene(curGene, end, queue, visited, bankSet, genes)) {
                    return step + 1;
                }
            }
            step++;
        }
        return 0;
    }

    public boolean changeGene(String curGene,String endGene, Queue<String>queue, Set<String>visited, Set<String>bankSet, char[] genes){
        char[] chars = curGene.toCharArray();
        for (int i = 0; i < endGene.length(); i++) {
            char oriChar = chars[i];
            for (char gene : genes) {
                if (oriChar == gene) continue;

                chars[i] = gene;
                String nextGene = String.valueOf(chars);
                if (bankSet.contains(nextGene)){
                    if (nextGene.equals(endGene)) return true;
                    if (!visited.contains(nextGene)){
                        queue.add(nextGene);
                        visited.add(nextGene);
                    }
                }
            }
            chars[i] = oriChar;
        }
        return false;
    }

}
