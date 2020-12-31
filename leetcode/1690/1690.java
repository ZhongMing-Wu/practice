class Solution {
    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n];
        preSum[0] = stones[0];
        for(int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }

        int[][] dp = new int[n][n];
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(preSum[j] - preSum[i] - dp[i + 1][j], preSum[j - 1] - preSum[i] + stones[i] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }
}