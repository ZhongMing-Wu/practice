### to 354 of leetcode website

[Russian Doll Envelopes](https://leetcode-cn.com/problems/russian-doll-envelopes/)

**小结**
- 这道题是最长递增子序列的二维问题，必须熟悉如何求解一维最长递增子序列问题，核心思想是使用一个dp数组，第 i 位存储的值表示长度为 i 的子序列当前的上限值(这个值相对越小越好)。