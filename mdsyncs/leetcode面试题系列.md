## leetcode上面试109题集合



#### [面试题 01.01. 判定字符是否唯一](https://leetcode-cn.com/problems/is-unique-lcci/)

> 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
>
> 示例 1：
>
> 输入: s = "leetcode"
> 输出: false 
> 示例 2：
>
> 输入: s = "abc"
> 输出: true
> 限制：
>
> 0 <= len(s) <= 100
> 如果你不使用额外的数据结构，会很加分。
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/is-unique-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

思路一：

```java
    // 拿到这道题第一反应是，从头到尾遍历元素，用set存储遍历过的元素
    // 继续遍历时，如果set中保存了，说明重复了
    // 如果set中没有保存，则将当前元素加入set中
    // 遍历完整个字符串，都没重复，说明没有重复
    // 算法时间复杂度 O(N)遍历一遍字符串。
    // 空间复杂度O(N) 使用了额外的set存储空间
    public boolean isUnique1(String astr) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < astr.length(); i++) {
            char c = astr.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }
```

##### 思路二：

```java
    // 但是题目中有限制
    // 如果不使用额外的数据结构，会很加分
    // 如何不使用额外的数据结构，时间复杂度还尽量低呢？
    // 不使用set，而使用暴力解法？
    // 可以，但是时间复杂度  O(N ^ 2)。
    public boolean isUnique2(String astr) {
        for (int i = 0; i < astr.length() - 1; i++) {
            char c1 = astr.charAt(i);
            for (int j = i + 1; j < astr.length(); j++) {
                char c2 = astr.charAt(j);

                if (c1 == c2) return false;
            }
        }
        return true;
    }
```

##### 思路三：

```java
    // 基于bool数组的方法
    // 猜测题目中的字符为26个字母
    // 初始化数组存储26个元素，初始值都为0
    // 一层循环，取出对应字符 - 'a' 对应数组的下标
    // 取出该下标的元素，如果值为1， 则重复，返回false
    // 如果值为0， 则将其置为1。
    // 如果遍历完整个字串，都没有重复，则返回true
    // 时间复杂度O(N), 空间复杂度为O(1)常数阶
    // 但是仍然使用了 额外的数据结构 数组
    public boolean isUnique3(String astr) {
        boolean[] bools = new boolean[26];
        for (int i = 0; i < astr.length(); i++) {
            int index = astr.charAt(i) - 'a';
            if (bools[index] == true) return false;
            bools[index] = true;
        }
        return true;
    }
```



##### 思路四：

```java
    // leetcode大神思路
    // 基于位运算的方法
    // 使用一个int类型的变量，来替代长度为26的bool数组。
    // 假设这个变量占26位(java中占32位)， 则26为都初始化为0
    // 26位对应26个字符
    // 遍历字符串
    // 首先计算当前字符与'a'的距离，用move_bit表示
    // 那么使用左移运算符 1<<move_bit 可以得到对应下标为1，其余下标为0的数
    // 用位运算mark | (1 << mov_bit) 将对应下标置为1
    public boolean isUnique(String astr){
        int mark = 0;
        for (int i = 0; i < astr.length(); i++) {
            int move_bit = 1<<(astr.charAt(i) - 'a');
            if ((mark & move_bit) != 0) return false;
            mark |= move_bit;
        }
        return true;
    }
```



#### [面试题 01.02. 判定是否互为字符重排](https://leetcode-cn.com/problems/check-permutation-lcci/)

> 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
>
> 示例 1：
>
> 输入: s1 = "abc", s2 = "bca"
> 输出: true 
> 示例 2：
>
> 输入: s1 = "abc", s2 = "bad"
> 输出: false
> 说明：
>
> 0 <= len(s1) <= 100
> 0 <= len(s2) <= 100
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/check-permutation-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 思路一：

```java
 // 1,分别对两个字符串char数组
    // 2,排序两个数组
    // 3,循环比较两个数组的每一位，不一致return false
    // 4,比较到最后，都相同。 return true
    public boolean CheckPermutation(String s1, String s2) {

        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        char[]c1 = s1.toCharArray();
        char[]c2 = s2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }
```



###### 思路二：

```java
// 用数组存储，字符串中每个字符的出现次数。
    // 遍历字符串
    // s1中存在的字符， 数组对应位置 + 1
    // s2中存在的字符， 数组对应位置 - 1
    // 最后，遍历数组，有不为0的位置，则不是重排
    // 所有位置都为0， 则true
    public boolean CheckPermutation1(String s1, String s2){
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int[] tmp = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            tmp[s1.charAt(i)] ++;
            tmp[s2.charAt(i)] --;
        }

        for (int i = 0; i < 256; i++) {
            if (tmp[i] != 0) return false;
        }

        return true;
    }
```



#### [面试题 01.03. URL化](https://leetcode-cn.com/problems/string-to-url-lcci/)

> URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
>
> 示例1:
>
>  输入："Mr John Smith    ", 13
>  输出："Mr%20John%20Smith"
> 示例2:
>
>  输入："               ", 5
>  输出："%20%20%20%20%20"
> 提示：
>
> 字符串长度在[0, 500000]范围内。
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/string-to-url-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



###### 思路一：

java库函数, 替换

```java
    // Java 库函数
    public String replaceSpaces(String S, int length) {
        return S.substring(0,length).replace(" ","%20");
    }
```

###### 思路二：

StringBuilder拼接

```java
    // StringBuilder拼接字符串
    public String replaceSpaces2(String S, int length){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            if (S.charAt(length) == ' '){
                // 空格
                sb.append("%20");
            }else {
                sb.append(S.charAt(i));
            }

        }
        return sb.toString();
    }
```



###### 思路三：

转字符串数组

```java
// 转字符数组
    public String replaceSpaces1(String S, int length) {
        char[] chars = S.toCharArray();

        int index = chars.length - 1;
        for (int i = length - 1; i >= 0; i--) {
            if (chars[i] == ' '){
                chars[index --] = '0';
                chars[index --] = '2';
                chars[index --] = '%';
            }else {
                chars[index --] = chars[i];
            }
        }

        while (index >= 0){
            chars[index --] = ' ';
        }

        return String.valueOf(chars).trim();
    }
```



#### [面试题 01.04. 回文排列](https://leetcode-cn.com/problems/palindrome-permutation-lcci/)

##### 

> 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
>
> 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
>
> 回文串不一定是字典当中的单词。
>
>  
>
> 示例1：
>
> 输入："tactcoa"
> 输出：true（排列有"tacocat"、"atcocta"，等等）
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

能排列成回文排列的，有两种情况

- 每个字符都是 2 个
- 除中间字符外，其余字符都是 2 个
- 用 HashSet 保存遍历过的字符串
- 遍历时，如果set中包含， 则把set中包含的删除
- 如果不包含， 则加入set
- 最终set中有0个 或者 1个元素， 则为回文排列

代码如下:

```java
    // 能排列成回文排列的, 有两种情况
    // 1， 每个字符都是两个
    // 2， 中间字符1个，其余字符都是两个
    // 所以 遍历字符串，Set中不包含元素时，添加。
    // Set中包含元素时，把包含的元素删除(抵消)
    // 最终Set中有 0个 或者 1个元素。则为回文排列
    public boolean canPermutePalindrome(String s) {

        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)){
                set.remove(c);
            }else {
                set.add(c);
            }
        }

        return set.size() <= 1;
    }
```

时间复杂度 : O(N) 遍历一遍字串

空间复杂度 : O(N)  额外的set存储空间



#### [面试题 01.05. 一次编辑](https://leetcode-cn.com/problems/one-away-lcci/)

> 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
>
>  
>
> 示例 1:
>
> 输入: 
> first = "pale"
> second = "ple"
> 输出: True
>
>
> 示例 2:
>
> 输入: 
> first = "pales"
> second = "pal"
> 输出: False
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/one-away-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



#### [面试题 01.05. 一次编辑](https://leetcode-cn.com/problems/one-away-lcci/)

##### 题解：

分三种情况:

- 两个字符串长度 相等， 则只有1次或者0次替换。 遍历记录字符不同的次数，diffcnt <= 1时 return true. 否则 return false 
- 两个字符串长度 相差超过1， 一次编辑不能处理，直接return false
- 两个字符串长度 相差哦等于1， 则为一次删除/添加



代码如下：

```java
public boolean oneEditAway(String first, String second) {

        int len1 = first.length();
        int len2 = second.length();

        // 长度相差超过1， 一次编辑不能处理
        if (Math.abs(len1 - len2) > 1) return false;

        if (len1 == len2){
            // 长度相同
            // 一次编辑 -> 替换
            int diffcnt = 0;
            for (int i = 0; i < len1; i++) {
                if (first.charAt(i) != second.charAt(i))  diffcnt ++;
            }
            return diffcnt <= 1;
        }else {

            // 保证 first串 比 second串 长
            if (len2 > len1) return oneEditAway(second,first);

            // 长度相差 1
            // 一次编辑 -> 添加/删除
            // 双指针，i用来遍历 first
            // j 用来遍历 second
            int i = 0, j = 0;
            while (i < len1 && j < len2){
                char c1 = first.charAt(i);
                char c2 = second.charAt(j);

                // 相等 两个指针都向右移动一位
                if (c1 == c2){
                    i ++;
                    j ++;
                    continue;
                }

                // 已经有一次不想等，指针后移，再次不想等， 直接return false. 
                if (i != j) return false;

                // 第一次不相等
                i ++;
            }
        }

        return true;
    }
```



时间复杂度: O(m + n)

空间复杂度: O(1)



#### [面试题 01.06. 字符串压缩](https://leetcode-cn.com/problems/compress-string-lcci/)

> 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
>
> 示例1:
>
>  输入："aabcccccaaa"
>  输出："a2b1c5a3"
> 示例2:
>
>  输入："abbccd"
>  输出："abbccd"
>  解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
> 提示：
>
> 字符串长度在[0, 50000]范围内。
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/compress-string-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

###### 题解：

这道题非常简单，从头到尾遍历字符串，用StringBuilder拼接。

- StringBuilder拼接字符串
- 从头到尾遍历字符串
- 记录上一次遍历时的字符 以及上一次遍历字符的 个数
- 当前遍历和上次记录字符一致时，sum++
- 当前遍历和上次不一致时，sb 拼接 sum， 重置sum。 prev重新赋值
- 最终新拼接字符串比 原始字符S小时，返回 sb.toString(), 否则返回原始字符 S



代码如下：

```java
// 非常基础的题目
    // StringBuilder拼接字符串
    // 从头到尾遍历 字符串
    // 上一次遍历的不一致时，记录上一个的次数。
    // 并重置次数， 且prev 置为新字符
    // 最终返回 新字符 和 旧字符 两者中，较短者
    public String compressString(String S) {

        if (S == null || S.length() <= 2) return S;

        char prev = S.charAt(0);
        int sum = 1;

        StringBuilder sb = new StringBuilder();
        sb.append(prev);

        for (int i = 1; i < S.length(); i++) {
            if (prev == S.charAt(i)){
                sum ++;
                continue;
            }

            sb.append(sum);

            prev = S.charAt(i);
            sum = 1;

            sb.append(prev);
        }

        String result = sb.toString();
        if(result.length() >= S.length()) return S;
        return result;
    }
```

时间复杂度 : O(N)

空间复杂度 : O(1)



#### [面试题 01.07. 旋转矩阵](https://leetcode-cn.com/problems/rotate-matrix-lcci/)

> 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
>
> 不占用额外内存空间能否做到？
>
> 示例 1:
>
> 给定 matrix = [ [1,2,3], [4,5,6], [7,8,9] ],
>
> 原地旋转输入矩阵，使其变为: [ [7,4,1], [8,5,2], [9,6,3] ] 示例 2:
>
> 给定 matrix = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
>
> 原地旋转输入矩阵，使其变为: [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]
>
> 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/rotate-matrix-lcci 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

```java
  public void rotate(int[][] matrix) {

        int n = matrix.length;
        for (int i = 0; i < matrix.length >> 1; i++) {
            for (int j = i; j < matrix.length - 1 - i; j++) {

                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
        }
    }
    
```

时间复杂度 ：O(N ^ 2)

空间复杂度 : O(1)



#### [面试题 01.08. 零矩阵](https://leetcode-cn.com/problems/zero-matrix-lcci/)

##### 题解：

- 开辟两个一维存放bool值数组
- line数组 用来存放 需要清空的行
- column数组 用来存放 需要清空的列
- 1，遍历矩阵。 把需要清空的line 和 column在数组中赋值为 true
- 2，把line数组中需要清空的行，清零
- 3，把column数组中需要清空的列，清零

代码如下：

```java
public void setZeroes(int[][] matrix) {

    // 需要清空的行数组
    boolean[] line  = new boolean[matrix.length];
    // 需要清空的列数组
    boolean[] column = new boolean[matrix.length];

    for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[i][j] == 0){
                line[i] = true;
                column[j] = true;
            }
        }
    }

    // 需要清零的行 置为零
    for (int i = 0; i < matrix.length; i++) {
        if (line[i] == true){
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }
    }

    // 需要清零的列 置为零
    for (int i = 0; i < matrix[0].length; i++) {
        if (column[i] == true){
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = 0;
            }
        }
    }
```

时间复杂度: O(M * N)

空间复杂度: O(M + N)



#### [面试题 01.09. 字符串轮转](https://leetcode-cn.com/problems/string-rotation-lcci/)

> 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
>
> 示例1:
>
>  输入：s1 = "waterbottle", s2 = "erbottlewat"
>  输出：True
> 示例2:
>
>  输入：s1 = "aa", "aba"
>  输出：False
> 提示：
>
> 字符串长度在[0, 100000]范围内。
> 说明:
>
> 你能只调用一次检查子串的方法吗？
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/string-rotation-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

###### 思路一：

暴力法：

- 如果s2是由s1旋转而来  则从头到尾开始旋转s1
- 遍历旋转0位(原串)， 1位...直至最后
- 如果遍历构成中，有跟s2相同的字符串，则return true
- 遍历一遍完后，都没有相同字符串，则return false

```Java
// 暴力法
    // 如果 s2 是由 s1 旋转而来 则从头到位开始旋转s1
    // 遍历旋转0位(原字串)， 1位... 直到旋转到倒数第二位
    // 如果遍历过程中，有跟s2相同的字符串， 则return true
    // 遍历一遍完后，都没有相同字符串， 则return false
    public boolean isFlipedString1(String s1, String s2) {

        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < s1.length()){

            sb.delete(0,sb.length());
            sb.append(s1.substring(i, s1.length()));
            sb.append(s1.substring(0,i));
            if (sb.toString().equals(s2)) return true;
            i++;
        }
        return false;
    }
```

![image-20200513131156369](https://tva1.sinaimg.cn/large/007S8ZIlly1geqqvnvv6zj30o606k0ti.jpg)

此方法执行时间38ms， 有大量的拼接，比较操作。 



###### 思路二：

- 题目中有说明， 你能只调用一次检查子串的方法吗？
- 显然上述解题，不符合检查一次字串的要求。拼接了N次字串，比较了N次字串

- 上述方法时间复杂度 : O(N ^ 2)

- 空间复杂度 : O(1)

- 时间复杂度非常高，算法虽过了，但是用时很长

- 接下来，我们尝试做时间复杂度的优化

  

- 思路是 裁剪字串成两个子串
- 从头到位比较,当字串s1 i位置， 与字串s2的0位置，字符相同时，根据i分割两个字串
- 如果s1 和 s2 分割后，两部分分别都相同，说明是旋转字串
- 如果不相同， 则往下遍历下一个与 s2 0位置相同的元素。
- i遍历完， 即与s2 0号位置相同的字符又两部分分别相同的字串。 则return false
- 我们的时间复杂度有了大幅度的提升，截图中可以看出，由之前的40ms左右，提高到1ms左右。
-  时间复杂度 : O(N)
- 空间复杂度 : O(1)

![屏幕快照 2020-05-13 下午12.55.01](https://tva1.sinaimg.cn/large/007S8ZIlly1geqqz7nri2j30qa0620tl.jpg)

```java
		// 题目中有说明， 你能只调用一次检查子串的方法吗？
    // 显然上述解题，不符合检查一次字串的要求。拼接了N次字串，比较了N次字串
    // 上述方法时间复杂度 : O(N ^ 2)
    // 空间复杂度 : O(1)
    // 时间复杂度非常高，算法虽过了，但是用时很长
    // 接下来，我们尝试做时间复杂度的优化

    // 思路是 裁剪字串成两个子串
    // 从头到位比较,当字串s1 i位置， 与字串s2的0位置，字符相同时，根据i分割两个字串
    // 如果s1 和 s2 分割后，两部分分别都相同，说明是旋转字串
    // 如果不相同， 则往下遍历下一个与 s2 0位置相同的元素。
    // i遍历完， 即与s2 0号位置相同的字符又两部分分别相同的字串。 则return false

    // 我们的时间复杂度有了大幅度的提升，截图中可以看出，由之前的40ms左右，提高到1ms左右。
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public boolean isFlipedString2(String s1, String s2){

        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        int i = 0,len = s1.length();
        while ((i < len)){
            if (s1.charAt(i) != s2.charAt(0)){
                i ++;
                continue;
            }

            String l_s1 = s1.substring(0,i);
            String r_s1 = s1.substring(i,len);

            String l_s2 = s2.substring(0,len - i);
            String r_s2 = s2.substring(len - i,len);

            if (l_s1.equals(r_s2) && r_s1.equals(l_s2)) return true;
            i++;
        }

        return false;
    }
```



###### 思路三：

- 以下为leetcode大神解题思路
- 如果 s2 是由 s1旋转而来，则 s1 + s1 必然包含 s2 ⚠️⚠️⚠️
- 想到了字符串拼接可能能解决问题，没想到这种思路
- 感觉费半天劲，牛逼的思路一行代码搞定了，差距非常大啊😓😓😓
- 非常6，双百操作。

```java
    public boolean isFlipedString(String s1, String s2){
        if ((s1 == null && s2 == null) || (s1.length() == 0 && s2.length() == 0)) return true;
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        
        return (s1 + s1).contains(s2);
    }
```

![屏幕快照 2020-05-13 下午1.07.44](https://tva1.sinaimg.cn/large/007S8ZIlly1geqr3gck8qj30s406c0tl.jpg)





#### [面试题 02.01. 移除重复节点](https://leetcode-cn.com/problems/remove-duplicate-node-lcci/)

##### 题解：

思路一：

```java
// 思路一
    // 用set存储，已经遍历过的元素
    // 遍历节点，set中包含时，则删除节点
    // set中不包含时，将节点值加入set
    // 时间复杂度O(N)
    // 空间复杂度O(N)
    // 空间换时间
    HashSet<Integer> set = new HashSet<>();
    public ListNode removeDuplicateNodes(ListNode head) {

        ListNode prev = null;
        ListNode node = head;
        while (node != null){
            if (set.contains(node.val)){
                // 删除node
                prev.next = node.next;
            }else {
                set.add(node.val);
                prev = node;
            }
            node = node.next;
        }

        return head;
    }
```

###### 思路二：

```java

    // 思路二：
    // 不使用额外的存储空间
    // 两遍循环
    // [1, 2, 3, 3, 2, 1]
    // 时间复杂度 : O(N ^ 2)
    // 空间复杂度 : O(1)
    public ListNode removeDuplicateNodes1(ListNode head){

        if (head == null) return null;

        ListNode node = head;
        while (node != null){
            ListNode cur = node;
            while (cur.next != null) {
                if (node.val == cur.next.val) {
                    cur.next = cur.next.next;
                }else {
                    cur = cur.next;
                }
            }
            node = node.next;
        }

        return head;
    }
```



#### [面试题 02.02. 返回倒数第 k 个节点](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/)

##### 题解：

- 此题与 **题目19** 基本一样
- 利用快慢指针思想
- 开始快慢指针都指向head
- 快指针往前走k步时，慢指针开始走
- 当快指针指向null时，慢指针就指向第k个节点
- 然后把 慢指针的val返回即可

代码如下：

```java
 public int kthToLast(ListNode head , int k){

        if (head.next == null) return head.val;

        ListNode fast = head;
        ListNode slow = head;

        int step = 0;
        while (fast != null){

            if (step >= k) slow = slow.next;
            fast = fast.next;
            step ++;
        }

        return slow.val;
    }
```



#### [面试题 02.03. 删除中间节点](https://leetcode-cn.com/problems/delete-middle-node-lcci/)

> 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
>
>  
>
> 示例：
>
> 输入：单向链表a->b->c->d->e->f中的节点c
> 结果：不返回任何数据，但该链表变为a->b->d->e->f
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/delete-middle-node-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

删除当前传入的中间节点(非第一个，和最后一个节点)，我们不知道其前驱元素，所以把当前传入元素的值，置为当前元素下一个元素的值，当前元素的next指向其next.next即可。

代码如下:

```java
public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
```

![屏幕快照 2020-05-14 下午12.47.36](https://tva1.sinaimg.cn/large/007S8ZIlly1gerwl5m9snj30p2068752.jpg)





#### [面试题 02.04. 分割链表](https://leetcode-cn.com/problems/partition-list-lcci/)

> 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
>
> 示例:
>
> 输入: head = 3->5->8->5->10->2->1, x = 5
> 输出: 3->1->2->10->5->5->8
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/partition-list-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

思路很简单:

- 创建smallHead 串起来比 x小的节点
- 创建bigHead 穿起来比 x 大的节点
- 最后把 smallHead 和 bigHead 串起来返回即可



代码如下:

```java
public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(-1);
        ListNode smallNode = smallHead;

        ListNode bigHead = new ListNode(-1);
        ListNode bigNode = bigHead;
        while (head != null){

            if (head.val < x){
                smallNode.next = head;
                smallNode = smallNode.next;
            }else{
                bigNode.next = head;
                bigNode = bigNode.next;
            }

            head = head.next;
        }

        bigNode.next = null;
        smallNode.next = bigHead.next;
        return smallHead.next;
    }
```

时间复杂度: O(N) 

空间复杂度 : O(N)

![屏幕快照 2020-05-14 下午1.40.28](https://tva1.sinaimg.cn/large/007S8ZIlly1gerxn21t4oj30ps078gme.jpg)



#### [面试题 02.05. 链表求和](https://leetcode-cn.com/problems/sum-lists-lcci/)

```java
给定两个用链表表示的整数，每个节点包含一个数位。

这些数位是反向存放的，也就是个位排在链表首部。

编写函数对这两个整数求和，并用链表形式返回结果。

 

示例：

输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
输出：2 -> 1 -> 9，即912
进阶：假设这些数位是正向存放的，请再做一遍。

示例：

输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
输出：9 -> 1 -> 2，即912

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/sum-lists-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 题解：

串链表

- 初始化 newHead 作为新链表的虚拟头结点
- 变量carry 记录是否上一个值有无进位
- 当L1 或者 L2不为空时，遍历L1, L2
- 当前sum = L1.val + L2.val + carry. 当然当L1 或者 L2 为空时，其值为0
- 根据sum创建节点，拼接在newHead上
- 遍历完毕，如果carry > 0, 代表末尾需要拼上 carry
- 最终返回 newHead.next

代码如下：

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode newHead = new ListNode(-1);
        ListNode node = newHead;
        int carry = 0;
        while (l1 != null || l2 != null){
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + carry;
            carry = 0;
            if (sum >= 10){
                sum -= 10;
                carry = 1;
            }

            node.next = new ListNode(sum);
            node = node.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        if (carry > 0){
            node.next = new ListNode(carry);
        }

        return newHead.next;
    }
```

时间复杂度: O(N)
空间复杂度: O(N)

![屏幕快照 2020-05-14 下午5.04.33](https://tva1.sinaimg.cn/large/007S8ZIlly1ges3hnnib4j30ok05st9h.jpg)





#### [面试题 02.06. 回文链表](https://leetcode-cn.com/problems/palindrome-linked-list-lcci/)

##### 题解：

###### 思路一:

代码如下：

```java
// 使用栈，加入前一半元素，再与后一半元素逐个对比
    // 时间复杂度 O(N) 遍历一遍链表元素
    // 空间复杂度 O(N) 使用了额外的栈存储控件
    public boolean isPalindrome1(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;

        // 前一半入栈
        while (fast != null && fast.next != null){

            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        // 说明节点总数是 单数
        if (fast != null) slow = slow.next;

        // 栈中元素和后一半元素对比
        // 不一致， return false
        // 全部一致， return true
        while (slow != null){
            if (stack.pop() != slow.val) return false;
            slow = slow.next;
        }

        return true;
    }
```

###### 思路二：

代码如下：

```java

    // 前边相同，找到链表中间节点
    // 翻转中间节点
    // 依次比较头结点 和 中间节点
    // 有不一致的则 return false
    // 全部都一致，则 return true
    // 时间复杂度 : O(N) 遍历一整遍 链表
    // 空间复杂度 : O(1) 没有使用额外内存控件
    public boolean isPalindrome(ListNode head) {

        // 找到中间元素
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // 说明节点总数是 单数
        if (fast != null) slow = slow.next;

        // 翻转slow
        ListNode newSlow = null;
        while (slow != null){
            ListNode tmp = slow.next;
            slow.next = newSlow;
            newSlow = slow;
            slow = tmp;
        }

        while (newSlow != null){

            if (head.val != newSlow.val) return false;

            head = head.next;
            newSlow = newSlow.next;
        }

        return true;
    }
```

第二种算法明显比前一种算法更优秀， 时间复杂度都是O(N) ， 但是其空间复杂度优化至O(1). 在leetcode上提交代码后，也有所提现。

如图，分别是方法一,方法二执行用时以及内存消耗

![屏幕快照 2020-05-14 下午5.50.12](https://tva1.sinaimg.cn/large/007S8ZIlly1ges4kw7hbjj30p405k751.jpg)

![屏幕快照 2020-05-14 下午5.43.39](https://tva1.sinaimg.cn/large/007S8ZIlly1ges4ih0qr9j30rg06aq3q.jpg)



#### [面试题 02.08. 环路检测](https://leetcode-cn.com/problems/linked-list-cycle-lcci/)

> 给定一个有环链表，实现一个算法返回环路的开头节点。
> 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
>
>
> 示例 1：
>
> 输入：head = [3,2,0,-4], pos = 1
> 输出：tail connects to node index 1
> 解释：链表中有一个环，其尾部连接到第二个节点。
>
> 示例 2：
>
> 输入：head = [1,2], pos = 0
> 输出：tail connects to node index 0
> 解释：链表中有一个环，其尾部连接到第一个节点。
>
> 示例 3：
>
> 输入：head = [1], pos = -1
> 输出：no cycle
> 解释：链表中没有环。
>
> 进阶：
> 你是否可以不用额外空间解决此题？
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/linked-list-cycle-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。



##### 题解：

###### 思路一：

使用 set 存储遍历过的节点

```java
    // set存储遍历过的节点
    // head节点，依次往下遍历
    // 遍历当前节点时，如果set中包含，当前节点就是 环路节点
    // 遍历完链表，没有set中包含的，说明没环。 返回null
    // 因为用到了额外set存储空间，所以时间复杂度比较高
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(N)
    // 题干中进阶 要求 ： 不使用额外存储空间
    // 接下来我们尝试使用 O(1) 的时间复杂度 解决此题目
    public ListNode delegeCycle1(ListNode head){

        HashSet<ListNode> set = new HashSet<>();
        while (head != null){
            if (set.isEmpty()){
                set.add(head);
                head = head.next;
                continue;
            }

            if (set.contains(head)) return head;
            set.add(head);

            head = head.next;
        }
        return null;
    }

```

###### 思路二：

快慢指针

```java
		// 快慢指针
    // 快指针一次走两步，慢指针一次走两步
    // 遍历链表， 条件当快指针不为空 且 快指针.next不为空时
    // 遍历过程中如果slow == fast了，就说明链表有环
    // 相等时，将slow指针置为head。 slow和fast均每次走一步，相遇时，即为环路节点
    // 遍历完毕，一直没有相遇，无环，返回null
    // 时间复杂度 : O(N)
    // 空间复杂度 : O(1)
    public ListNode detectCycle(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast){
                break;
            }
        }

        if (fast == null) return null;

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
```





#### [面试题 03.02. 栈的最小值](https://leetcode-cn.com/problems/min-stack-lcci/)

> 请设计一个栈，除了常规栈支持的pop与push函数以外，还支持min函数，该函数返回栈元素中的最小值。执行push、pop和min操作的时间复杂度必须为O(1)。
>
>
> 示例：
>
> MinStack minStack = new MinStack();
> minStack.push(-2);
> minStack.push(0);
> minStack.push(-3);
> minStack.getMin();   --> 返回 -3.
> minStack.pop();
> minStack.top();      --> 返回 0.
> minStack.getMin();   --> 返回 -2.
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/min-stack-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

- 使用两个栈
- 一个保存正常值，一个保存最小值
- push时需要注意， 如果minStack不为空，则minStack入栈 minStack.peek() 和 val 的较小者
- getMin时，直接返回minStack中的较小者即可。

```java
		// 利用两个栈，一个存放正常数据，一个存放最小数据
    // push的时候要注意，如果minStack不为空，minStack要入栈 其栈顶和当前元素中较小的
    // getMin时，直接返回minStack的栈顶元素即可
    Stack<Integer> normalStack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public _面试题_03_02_栈的最小值() {

        normalStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        normalStack.push(x);
        if (minStack.isEmpty()){
            minStack.push(x);
        }else {
            minStack.push(Math.min(minStack.peek(),x));
        }
    }

    public void pop() {
        normalStack.pop();
        minStack.pop();
    }

    public int top() {
        return normalStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
```



#### [面试题 03.04. 化栈为队](https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci/)

> 实现一个MyQueue类，该类用两个栈来实现一个队列。
>
>
> 示例：
>
> MyQueue queue = new MyQueue();
>
> queue.push(1);
> queue.push(2);
> queue.peek();  // 返回 1
> queue.pop();   // 返回 1
> queue.empty(); // 返回 false
>
> 说明：
>
> 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
> 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
> 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

- 使用两个队列实现栈
- push时，存入 q1
- pop
  - 如果q2不为空，q2队头出队，再返回即可。
  - 如果q2为空，q1逐个出队列，入队q2。 然后q2队头出队，再返回即可
- peek
  - 基本同 pop. 将pop()  换为 peek() 即可
- isEmpty
  - q1 和 q2都为空，则为空
  - 否则不为空

代码如下：

```java
// 利用两个队列 实现栈

    // push时，存入q1

    // pop时，如果q2不空， 则q2 移除队头元素并返回
    // 如果 q2为空， 则将q1的元素依次出队，再入队q2。 最后q2移除队头元素并返回

    // peek ，同pop同理，只是将pop操作 换为 peek即可

    // isEmpty ， 如果q1 和 q2都为空，则为空。 否则不为空

    Queue<Integer> q1;
    Queue<Integer> q2;
    /** Initialize your data structure here. */
    public _面试题_03_04_化栈为队() {

        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        q1.add(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!q2.isEmpty()){
            return q2.remove();
        }

        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }

        return q2.remove();
    }

    /** Get the front element. */
    public int peek() {
        if (!q2.isEmpty()){
            return q2.peek();
        }

        while (!q1.isEmpty()){
            q2.add(q1.remove());
        }

        return  q2.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

```



#### [面试题 04.02. 最小高度树

#### ](https://leetcode-cn.com/problems/minimum-height-tree-lcci/)

> 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
>
> 示例:
> 给定有序数组: [-10,-3,0,5,9],
>
> 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
>
>           0 
>          / \ 
>        -3   9 
>        /   / 
>      -10  5 
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/minimum-height-tree-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

思路：

- 升序数组，且生成二叉排序树
- 所以树的根节点，必为数组的中间元素(总个数偶数的数组，中间左右均可)
- 先取出树的根节点
- 再分别递归计算其 左，右子树

代码如下：

```java
// 思路：
    // 因为是升序数组，且生成二叉排序树
    // 所以树的根节点，是数组的中间元素(总个数为双数的数组，中间左右均可)
    // 先取出树的根节点
    // 再分别递归计算其 左，右子树
    public TreeNode sourtedArrayToBST(int[] nums, int left, int right){
        if (right - left <= 1) return null;

        int mid = (left + right) >> 1;
        TreeNode node = new TreeNode(nums[mid]);

        node.left = sourtedArrayToBST(nums,left,mid);
        node.right = sourtedArrayToBST(nums,mid,right);

        return node;
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return sourtedArrayToBST(nums,0,nums.length);
    }
```



#### [面试题 04.03. 特定深度节点链表](https://leetcode-cn.com/problems/list-of-depth-lcci/)

> 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
>
>  
>
> 示例：
>
> 输入：[1,2,3,4,5,null,7,8]
>
>         1
>        /  \ 
>       2    3
>      / \    \ 
>     4   5    7
>    /
>   8
>
> 输出：[[1],[2,3],[4,5,7],[8]]
>
> 来源：力扣（LeetCode）
> 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
> 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

##### 题解：

- 二叉树层序遍历
- 把每一层的数据串成一个链表
- 将每一个链表加入数组
- 最终返回数组即可

代码如下：

```java
 // 二叉树的层序遍历
    // 把每一层的元素串成一个链表
    // 将链表分别加入数组
    // 最终返回数组即可
    public ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) return null;

        ArrayList<ListNode> arrayList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(tree);

        while (!queue.isEmpty()){
            int size = queue.size();

            ListNode listnode = new ListNode(-1);
            ListNode cur = listnode;
            while (size > 0){

                TreeNode node = queue.remove();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);

                cur.next = new ListNode(node.val);
                cur = cur.next;
                size --;
            }

            arrayList.add(listnode.next);
        }

        return arrayList.toArray(ListNode[]::new);
    }
```





