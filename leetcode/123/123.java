class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[3][2];
        int curMin = 0;
        //这里将卖出股票作为交易一次
        for(int i = 0; i < prices.length; i++) {
            if(i == 0) {
                curMin = -prices[i];
                dp[1][0] = 0;
                dp[1][1] = -prices[i];
                dp[2][0] = 0;
                dp[2][1] = -prices[i];
            }
            else {
                dp[1][0] = Math.max(curMin + prices[i], dp[1][0]);
                dp[2][1] = Math.max(dp[2][0] - prices[i], dp[2][1]);
                dp[2][0] = Math.max(dp[1][1] + prices[i], dp[2][0]);
                dp[1][1] = Math.max(dp[1][0] - prices[i], dp[1][1]);
                curMin = Math.max(curMin, -prices[i]);
            }
        }
        return Math.max(dp[2][0], dp[1][0]) > 0 ? Math.max(dp[2][0], dp[1][0]) : 0;
    }
}