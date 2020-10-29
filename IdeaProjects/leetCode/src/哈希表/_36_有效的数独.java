package 哈希表;

import java.util.Arrays;
import java.util.HashSet;

/**
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

        数字 1-9 在每一行只能出现一次。
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


        上图是一个部分填充的有效的数独。

        数独部分空格内已填入了数字，空白格用 '.' 表示。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-sudoku
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class _36_有效的数独 {

    /**
     *
     * 遍历 board
     * 分别判断 每行 每列 每个九宫格是否有重复数组
     * 利用 HashSet去重
     * 时间复杂度 : 因为是九宫格 9 * 9 复杂度为常数级别 循环81次 常数级别
     * 空间复杂度 : 使用了3个set，每个最多存储 9个 元素         常数级别
     *
     * 时间上击败 50%左右的用户
     *
     * */
    public boolean isValidSudoku(char[][] board) {
        HashSet<Character>set1 = new HashSet<>();
        HashSet<Character>set2 = new HashSet<>();
        HashSet<Character>set3 = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            set1.clear();
            set2.clear();
            set3.clear();
            int m = i / 3 * 3;
            int n = i % 3 * 3;

            for (int j = 0; j < 9; j++) {
                char c1 = board[i][j];
                char c2 = board[j][i];
                if (c1 != '.'){
                    if (set1.contains(c1)) return false;
                    set1.add(c1);
                }
                if (c2 != '.'){
                    if (set2.contains(c2)) return false;
                    set2.add(c2);
                }
                int ver = m + j % 3;
                int hor = n + j / 3;
                char c = board[hor][ver];
                if (c != '.'){
                    if (set3.contains(c)) return false;
                    set3.add(c);
                }
            }
        }
        return true;
    }

    /**
     *
     * 优化 :
     * 因为数字取值范围都是 [1, 9]
     * 所以想到使用数组代替哈希Set，提高效率的方式
     *
     * 时间，空间复杂度仍然是常数级别
     *
     * 优化过后，击败 96.38%的用户
     *
     * */
    public boolean isValidSudoku1(char[][] board) {
        boolean[] nums1 = new boolean[10];
        boolean[] nums2 = new boolean[10];
        boolean[] nums3 = new boolean[10];
        for (int i = 0; i < 9; i++) {
            Arrays.fill(nums1,false);
            Arrays.fill(nums2,false);
            Arrays.fill(nums3,false);

            int m = i / 3 * 3;
            int n = i % 3 * 3;

            for (int j = 0; j < 9; j++) {
                int ver = m + j % 3;
                int hor = n + j / 3;
                char c1 = board[i][j];
                char c2 = board[j][i];
                char c3 = board[hor][ver];

                if (contains(nums1, c1)) return false;
                if (contains(nums2, c2)) return false;
                if (contains(nums3, c3)) return false;
            }
        }
        return true;
    }

    public boolean contains(boolean[] nums, char c){
        if (c == '.') return false;
        c -= '0';
        if (nums[c]) return true;
        nums[c] = true;
        return false;
    }

}
