class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int m = nums.length;
        int[][] dp = new int[m][m];

        for(int i = 0; i < m; ++i) {
            dp[i][i] = nums[i];
        }

        for(int i = 2; i <= m; ++i) {
            for(int j = 0; j <= m - i; ++j) {
                dp[j][j + i - 1] = Math.max(nums[j] - dp[j + 1][j + i - 1], nums[j + i - 1] - dp[j][j + i - 2]);
            }
        }
        return dp[0][m - 1] >= 0 ? true : false;
    }
}