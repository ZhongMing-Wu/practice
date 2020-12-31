### to 730 of leetcode website

[Count Different Palindromic Subsequences](https://leetcode-cn.com/problems/count-different-palindromic-subsequences/)

**小结**
- 对于DP问题，在处理一个一维数组或字符串时，一种方式是用一个二维数组记录其子数组(子字符串)的情况。例：dp[i][j] 从下标 i 到 j 的子字符串的情况
- 对于int型变量值越界的问题，如果最终结果要求通过取余来处理，在处理过程中，可以忽略越界的情况，在最后得到结果时，如果变量越界，直接加上用于取余的值即可，如果变量不越界，则直接取余。例：
```
// 如果dp[i][j] 小于 0，加上取余的值，如果大于 0 ，则直接取余
dp[i][j] = dp[i][j] < 0? dp[i][j] + modNum : dp[i][j] % modNum;