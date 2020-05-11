## AVL树



- 平衡因子(Balance Factor): 某节点左右子树的高度差
- AVL树的特点
  - 每个节点的平衡因子只可能是1, 0, -1(绝对值 <= 1, 如果超过1，称之为 "失衡")
  - 每个节点的左右子树高度差不超过1
  - 搜索， 添加， 删除的时间复杂度是 O(log N)

![image-20200511153137854](https://tva1.sinaimg.cn/large/007S8ZIlly1geojoezaoxj30ti0gi7b5.jpg)



