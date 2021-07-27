### to 903 of leetcode website

[DI 序列的有效排列](https://leetcode-cn.com/problems/valid-permutations-for-di-sequence/)

**小结**
- 这道题的核心在于，当前最后一位的值为 val 时，以 D 举例(即 P[i - 1] > P[i])，这时 dp[i][val] 的值来源于前一位 dp[i - 1][preVal] 并且 preVal >= val 的和。这里的问题是 val 可能在前面出现过，这里之所以这种思路是正确的，是因为我们可以将 i - 1 位之前的所有大于等于当前 val 的值都 + 1，这时前 i - 1 为中不会包含 val，并且前 i - 1 位依旧满足条件(证明非常简单)，所以直接让 dp[i][j] += dp[i - 1][k]， 这里 k 是遍历的大于 val 的值的情况。代码中是利用了 dp[i][j + 1] 或者 dp[i][j - 1]减少了一轮循环，思路一样。
- 可以参考[图解弄清903的动态规划思路](https://leetcode-cn.com/problems/valid-permutations-for-di-sequence/solution/tu-jie-nong-qing-dong-tai-gui-hua-jie-fa-by-mo-mo-/)的线段图