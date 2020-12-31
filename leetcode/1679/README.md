### to 1679 of leetcode website

[Max Number of K-Sum Pairs](https://leetcode-cn.com/problems/max-number-of-k-sum-pairs/)

**小结**
- 该题有两种思路，一种是先排序，然后从左右同时遍历;一种是通过一个hashmap记录出现的元素。经测试，当访问hashmap的次数过多时，时间效率低于先排序后访问数组的效率。