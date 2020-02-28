class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            dp[i] = i;
        }
        int pre = 0;   //用于记录上一个可以完全开方的数
        for(int i = 1; i <= n; i++) {
            if((pre + 1) * (pre + 1) == i) {
                pre = pre + 1;
                dp[i] = 1;
            }
            else {
                for(int j = pre; j > 0; j--) {
                    dp[i] = Math.min(dp[i], dp[j * j] + dp[i - j * j]);
                }
            }
        }
        return dp[n];
    }
}