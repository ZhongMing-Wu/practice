class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }     
        int[] preSum = new int[n + 1];
        for(int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        dp[0][0] = 0;
        for(int i = 0; i <= n; ++i) {
            for(int j = 1; j <= Math.min(m, i); ++j) {
                for(int k = 0; k < i; ++k) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], preSum[i] - preSum[k]));
                }
            }
        }

        return dp[n][m];
    }
}