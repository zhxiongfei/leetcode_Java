package 堆;

/*
给定一个字符串，请将字符串里的字符按照出现的频率降序排列。

        示例 1:

        输入:
        "tree"

        输出:
        "eert"

        解释:
        'e'出现两次，'r'和't'都只出现一次。
        因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
        示例 2:

        输入:
        "cccaaa"

        输出:
        "cccaaa"

        解释:
        'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
        注意"cacaca"是不正确的，因为相同的字母必须放在一起。
        示例 3:

        输入:
        "Aabb"

        输出:
        "bbAa"

        解释:
        此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
        注意'A'和'a'被认为是两种不同的字符。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

import java.lang.reflect.Array;
import java.util.*;
import java.util.Map.Entry;

public class _451_根据字符出现频率排序 {

    public String frequencySort(String s) {

        // 组织字典，字典中存放为 <字母 : 出现次数>
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);

            if (hashMap.containsKey(character)){
                Integer cnt = hashMap.get(character);
                hashMap.put(character, ++cnt);
            }else {
                hashMap.put(character, 1);
            }
        }

        // 最大堆， 出现次数最多的元素排在堆顶
        PriorityQueue<Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Entry<Character, Integer>>() {
            @Override
            public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        // 把字典中的键值对，放入 最大堆
        for (Entry<Character, Integer>entry : hashMap.entrySet()){
            queue.offer(entry);
        }

        // 拼接最终结果
        StringBuilder stringBuilder = new StringBuilder();
        while (!queue.isEmpty()){

            Entry<Character, Integer> entry = queue.poll();
            for (int i = 0; i < entry.getValue(); i++) {
                stringBuilder.append(entry.getKey());
            }
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        _451_根据字符出现频率排序 test = new _451_根据字符出现频率排序();
        String s = test.frequencySort("tree");
        System.out.println(s);
    }
}
