package 堆;

/*
给一非空的单词列表，返回前 k 个出现次数最多的单词。

        返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。

        示例 1：

        输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
        输出: ["i", "love"]
        解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
        注意，按字母顺序 "i" 在 "love" 之前。
         

        示例 2：

        输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
        输出: ["the", "is", "sunny", "day"]
        解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
        出现次数依次为 4, 3, 2 和 1 次。

        注意：

        假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。
        输入的单词均由小写字母组成。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/top-k-frequent-words
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.*;
import java.util.Map.Entry;

public class _692_前K个高频单词 {

    public List<String> topKFrequent(String[] words, int k) {

        Map<String,Integer> counts = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            counts.put(words[i], counts.getOrDefault(words[i],0) +1);
        }

        // 最小堆
        PriorityQueue<Entry<String, Integer>> queue = new PriorityQueue<>(
                (Entry<String , Integer> e1, Entry<String , Integer> e2) -> {
                    return e1.getValue() == e2.getValue() ? e2.getKey().compareTo(e1.getKey()) : e1.getValue() - e2.getValue();
        });

        for (Entry<String,Integer> entry : counts.entrySet()){
            queue.offer(entry);
            if (queue.size() > k){
                queue.poll();
            }
        }

        List<String> res = new LinkedList<>();
        while (!queue.isEmpty()){
            res.add(0,queue.poll().getKey());
        }
        return res;
    }

    public static void main(String[] args) {

        _692_前K个高频单词 test = new _692_前K个高频单词();
        if (test != null){


        }
    }

}
