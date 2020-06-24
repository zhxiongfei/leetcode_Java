package 探索系列;

import java.util.LinkedList;
import java.util.List;

public class 探索递归 {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> lists = new LinkedList<>();
        if (numRows <= 0) return lists;

        int[] dp = new int[numRows + 1];

        for (int i = 1; i <= numRows; i++) {

            List<Integer> list = new LinkedList<>();
            for (int j = i-1; j >= 0; j--) {
                if (j == 0 || j == i-1){
                    dp[j] = 1;
                }else {
                    dp[j] = dp[j - 1] + dp[j];
                }

                if (dp[i] != 0)
                    list.add(dp[i]);
            }
            lists.add(list);
        }

        return lists;
    }

}
