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

