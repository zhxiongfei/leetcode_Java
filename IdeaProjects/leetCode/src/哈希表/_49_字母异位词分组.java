package 哈希表;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

        示例:

        输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出:
        [
        ["ate","eat","tea"],
        ["nat","tan"],
        ["bat"]
        ]
        说明：

        所有输入均为小写字母。
        不考虑答案输出的顺序。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/group-anagrams
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _49_字母异位词分组 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        HashMap<String,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<Integer>idxs = map.getOrDefault(key, new ArrayList<>());
            idxs.add(i);
            map.put(key, idxs);
        }

        for (String key : map.keySet()) {
            List<Integer>idxs = map.get(key);
            List<String>tmp = new ArrayList<>();
            for (Integer idx : idxs) {
                tmp.add(strs[idx]);
            }
            res.add(tmp);
        }
        return res;
    }

    public static List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;

        HashMap<String,List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String>strings = map.getOrDefault(key, new ArrayList<>());
            strings.add(strs[i]);
            map.put(key, strings);
        }

        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {

        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }
}
