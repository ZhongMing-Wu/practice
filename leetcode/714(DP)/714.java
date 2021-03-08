class Solution {
    public int maxProfit(int[] prices, int fee) {
        int m = prices.length;
        int[][] dp = new int[m][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i = 1; i < m; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[m - 1][0];
    }
}