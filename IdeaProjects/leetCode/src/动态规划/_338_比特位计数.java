package 动态规划;

public class _338_比特位计数 {

    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0){
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    /**
     * 每个数字计算
     * 时间复杂度 : O(N ^ 2)
     * 空间复杂度 : O(1)
     * */
    public int[] countBits1(int num) {
        int[]res = new int[num + 1];
        for (int i = 0; i <= num; i++){
            res[i] = hammingWeight(i);
        }
        return res;
    }

    /**
     * 动态规划
     * 时间复杂度 : O(N)
     * 空间复杂度 : O(N)
     * */
    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    public int[] countBits(int num) {
        int[]res = new int[num + 1];
        if (num == 0) return res;
        for (int i = 1; i <= num; i++) {
            res[i] = res[i >> 1] + (i & 1);
        }
        return res;
    }

    public static void main(String[] args) {
        _338_比特位计数 cls = new _338_比特位计数();
        cls.countBits(12);
    }

}
