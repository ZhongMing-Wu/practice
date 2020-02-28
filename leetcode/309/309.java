class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int status = 0;  //0表示未处于冷冻期，1表示处于冷冻期
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1; i < prices.length; i++) {
            if(status == 0) {   //说明i - 1那天未卖出股票
                if(dp[i - 1][0] < dp[i - 1][1] + prices[i]) {
                    dp[i][0] = dp[i - 1][1] + prices[i];
                    status = 1;
                }
                else {
                    dp[i][0] = dp[i - 1][0];
                }
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
            else {
                if(dp[i - 2][0] < dp[i - 2][1] + prices[i]) {
                    dp[i][0] = dp[i - 2][1] + prices[i];
                }
                else {
                    dp[i][0] = dp[i - 2][0];
                    status = 0;
                }
                dp[i][1] = Math.max(dp[i - 2][1], dp[i - 2][0] - prices[i]);
                
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][0]);
                dp[i][1] = Math.max(dp[i][1], dp[i - 1][1]);
            }
        }
        return dp[prices.length - 1][0];
    }
}