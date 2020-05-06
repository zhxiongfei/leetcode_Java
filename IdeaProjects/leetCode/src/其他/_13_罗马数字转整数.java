package 其他;

public class _13_罗马数字转整数 {
    public int romanToInt(String s) {
        int sum = 0;
        char pre = '0';
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == 'I') sum += 1;
            if (c == 'V') {
                if (pre == 'I') sum -= 2;
                sum += 5;
            }
            if (c == 'X') {
                if (pre == 'I') sum -= 2;
                sum += 10;
            }
            if (c == 'L') {
                if (pre == 'X') sum -= 20;
                sum += 50;
            }

            if (c == 'C') {
                if (pre == 'X') sum -= 20;
                sum += 100;
            }
            if (c == 'D'){
                if (pre == 'C') sum -= 200;
                sum += 500;
            }
            if (c == 'M') {
                if (pre == 'C') sum -= 200;
                sum += 1000;
            }

            pre = c;
        }

        return sum;
    }
}
