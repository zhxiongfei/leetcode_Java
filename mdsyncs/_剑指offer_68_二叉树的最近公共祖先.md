---
title: "_剑指offer_68_二叉树的最近公共祖先"
date: 2020-06-02T16:38:37+08:00
draft: true
---

# [面试题68 - I. 二叉搜索树的最近公共祖先](https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/)

```java
给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]



 

示例 1:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
输出: 6 
解释: 节点 2 和节点 8 的最近公共祖先是 6。
示例 2:

输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
输出: 2
解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 

说明:

所有节点的值都是唯一的。
p、q 为不同节点且均存在于给定的二叉搜索树中。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

## 题解：

### 思路一：

- 构建 <node : parent>字典
  - dfs 二叉树,  构建每个节点<节点 : 父节点>字典
- 分别记录, p 和 q节点的祖先节点，用list存储
  - getParents方法用来求出节点的所有祖先节点
- 两个祖先节点的list，最近的相交元素即为最近祖先节点

代码如下:

```java
/*
    *
    *  dfs二叉树， 字典中保存 <节点 : 父节点>
    *  每一个节点，都可以在字典中取出其父节点
    *
    *  getParents方法，用来获取某一节点的所有父节点
    *
    *  分别获取 p 和 q 的父节点数组
    *  取两个数组第一个相交的元素 即为最近公共祖先
    *
    * */
    HashMap<TreeNode, TreeNode> map = new HashMap<>();
    public void dfs(TreeNode node, TreeNode parent){
        if (node == null) return;

        map.put(node,parent);

        dfs(node.left, node);
        dfs(node.right, node);
    }

    public List getParents(TreeNode node){

        List<TreeNode> list = new ArrayList<>();

        list.add(node);
        TreeNode parent = map.get(node);
        while (parent != null){
            map.put(node, parent);

            node = parent;
            parent = map.get(node);

            list.add(node);
        }
        return list;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // 构建父节点字典
        dfs(root,null);

        // 获取 p 节点，祖先节点数组
        List<TreeNode> l1 = getParents(p);

        // 获取 q 节点，祖先节点数组
        List<TreeNode> l2 = getParents(q);

        // 找到两个数组中 第一个相交元素
        HashSet<TreeNode> set = new HashSet(l1);
        for (int i = 0; i < l2.size(); i++) {
            TreeNode node = l2.get(i);
            if (set.contains(node)) return node;
        }

        return root;
    }
```

复杂度分析 : 

时间复杂度 : O(N)

空间复杂度 : O(N)

![屏幕快照 2020-06-02 下午4.28.47](https://tva1.sinaimg.cn/large/007S8ZIlly1gfe1pp5ur4j30pa06e3za.jpg)



**二叉搜索树**?

貌似我们上述题解中，完全没有用到二叉搜索树的性质，就是按照普通的二叉树的求解的。

所以一定有更优的算法来解题。



### 思路二：

- 二叉搜索树的性质：左子树的值 < 根节点 < 右子树的值。
- 所以当 root的值 比 p 和 q的值都大时, 则最近公共祖先一定根节点的左边
- 当root的值 比 p 和 q的值都小时， 则最近公共祖先一定在跟节点右边
- 否砸，root就是最近公共祖先节点

代码如下:

```java
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor1(root.left, p, q);
        }else if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor1(root.right, p, q);
        }
        return root;
    }
```

复杂度分析 :

时间复杂度 : O(log N) 

空间复杂度 : O(log N) 递归调用深度为 log N层， 占用的栈空间 

![屏幕快照 2020-06-02 下午5.01.38](https://tva1.sinaimg.cn/large/007S8ZIlly1gfe1yczlzxj30oe06cmxw.jpg)



### 思路三：

同上述思路，**迭代法**

```java
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        while (root != null){

            if (root.val > q.val && root.val > p.val){
                root = root.left;
                continue;
            }else if (root.val < q.val && root.val < p.val){
                root = root.right;
                continue;
            }

            return root;
        }
        return null;
    }
```

![屏幕快照 2020-06-02 下午5.06.16](https://tva1.sinaimg.cn/large/007S8ZIlly1gfe22it38cj30nu05wmxv.jpg)