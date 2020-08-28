package 字符串;

public class _657_机器人能否返回原点 {

    public boolean judgeCircle(String moves) {
        if (moves == null || moves.length() == 1) return true;
        char[] chars = moves.toCharArray();
        int x = 0, y = 0;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == 'R') x ++;
            else if (c == 'L') x --;
            else if (c == 'U') y ++;
            else if (c == 'D') y --;
        }

        return x==0 && y==0;
    }
}

