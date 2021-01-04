package 回溯算法;

import java.util.*;

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

public class 电话号码的字母组合 {

    static int cnt;
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        HashMap<Character,String > map = new HashMap<Character, String>(){{
            put('2',"abc");
            put('3',"def");
            put('4',"ghi");
            put('5',"jkl");
            put('6',"mno");
            put('7',"pqrs");
            put('8',"tuv");
            put('9',"wxyz");
        }};

        StringBuilder path = new StringBuilder();
        boolean[] used = new boolean[digits.length()];

        dfs(res,path, used,map,digits, 0,0);

        return res;
    }

    public void dfs(List<String> res,StringBuilder path, boolean[] used, HashMap<Character,String > map, String digits,int begin, int depth){
        if (depth == digits.length()){
            res.add(new String(path));
            return;
        }

        for (int i = begin; i < digits.toCharArray().length; i++) {
            char c = digits.toCharArray()[i];
            String nums =  map.get(c);
            for (char c1 : nums.toCharArray()) {
                if (used[i]) continue;
                used[i] = true;
                path.append(c1);

                cnt ++;
                dfs(res, path, used, map, digits, i + 1,depth + 1);

                used[i] = false;
                path.deleteCharAt(path.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        电话号码的字母组合 cls = new 电话号码的字母组合();
        cls.letterCombinations("234");

        System.out.println(cnt);
    }
}
