package 二分查找;

public class _278_第一个错误的版本 {

    public boolean isBadVersion(int n){
        if (n == 4 || n == 5) return true;
        return false;
    }

    public int firstBadVersion(int begain ,int end){
        if (begain >= end) return begain;
        if (isBadVersion(begain)) return begain;

        int mid = (begain + end) >> 1;
        if (isBadVersion(mid) == false){
            // mid 没错
            // 查找 mid后边的错误版本
            return firstBadVersion(mid + 1, end);
        }else {
            // mid 错了
            // 如果 mid - 1没错。 则 mid 是错误开始版本
            if (isBadVersion(mid - 1) == false) return mid;
            // 否则 查找 mid - 1的前半部分
            return firstBadVersion(begain, mid - 1);
        }
    }

    // 递归
    public int firstBadVersion(int n) {
        return firstBadVersion(1, n);
    }

    // 迭代
    public int firstBadVersion1(int n){
        int low = 1, high = n;
        while (low < high){
            int mid = low + ((high - low) >> 1);
            if (isBadVersion(mid) == false){
                low = mid + 1;
            }else {
                if (isBadVersion(mid -1) == false) return mid;
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        _278_第一个错误的版本 cls = new _278_第一个错误的版本();
        int res = cls.firstBadVersion1(10);
        System.out.println(res);
    }
}
