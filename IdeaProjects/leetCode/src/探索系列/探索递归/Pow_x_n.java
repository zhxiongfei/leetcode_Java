package 探索系列.探索递归;

public class Pow_x_n {

    static double num = 0;
    public static double myPow(double x, int n) {
        if (x == 0) return x;
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1/x;

        if (num == 0) num = x;

        if (n < 0) {
            return myPow((x * num), ++n);
        }
        return myPow(x * num, --n);
    }

    public static void main(String[] args) {

        double res = myPow(2.0, -2);
        System.out.println(res);
    }

}
