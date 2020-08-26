package 回溯算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



        示例:

        输入："23"
        输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        说明:
        尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _17_电话号码的字母组合 {

    public List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }

        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backTrace(combinations, phoneMap, digits, new StringBuilder(), 0);
        return combinations;
    }

    public void backTrace(List<String>combinations, Map<Character,String>map, String digits, StringBuilder combination, int index){
        if (index == digits.length()){
            combinations.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = map.get(digit);
        int letternsCount = letters.length();
        for (int i = 0; i < letternsCount; i++){
            combination.append(letters.charAt(i));
            backTrace(combinations, map, digits,  combination, index + 1);
            combination.deleteCharAt(index);
        }
    }

}
