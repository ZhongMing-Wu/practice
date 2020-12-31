### to 1458 of leetcode website

[Max Dot Product of Two Subsequences](https://leetcode-cn.com/problems/max-dot-product-of-two-subsequences/)

**小结**
- 对于数组或字符串遍历问题，一种思路是使用 dp[i][j] 表示下标 i 到下标 j 的结果，然后最后算出d[0][n - 1] 即为最终结果
- 该题有两个数组，故最直观的思路是使用一个四维数组 dp[i][j][m][n] 表示数组1 i 到 j 并且数组2 m 到 n 的结果，最后算出dp[0][len1 - 1][0][len2 - 1] 即为最终结果。
- 但是该题中需要保证点乘是有序的，即在求 dp[i][j][m][n]时，只能变化 j 和 n(只能变化末位的位置)，前置位是不能改变的，所以可以将四维数组降到二维 dp[i][j] 表示数组1前 i 个值 和数组2前 j 个值得结果，最终算出dp[len1 - ][len2 - 1] 即为最终结果。
- 其核心在于找到正确的状态转移方程式。