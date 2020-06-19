package 探索头条;

public class 最长公共前缀 {

    public String longestCommonPrefix(String[] strs) {

        StringBuilder sb = new StringBuilder();
        if (strs == null || strs.length == 0) return sb.toString();
        if (strs.length == 1) return strs[0];

        int cnt = strs[0].length();
        for(int i=1; i< strs.length; i++){
            cnt = Math.min(cnt,strs[i].length());
        }

        // 外层循环-第i个字母
        for(int i=0; i< cnt; i++){
            char c = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                char c1 = strs[j].charAt(i);
                if(c != c1) return sb.toString();

                if (j == strs.length - 1) sb = sb.append(c1);
            }
        }

        return sb.toString();
    }

}
