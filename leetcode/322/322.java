class Solution {
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0) {
            return -1;
        }
        if(amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        for(int i = 1; i <= amount; i++) {
            for(int j = coins.length - 1; j >= 0; j--) {
                if(i - coins[j] > 0 && dp[i - coins[j]] != 0) {
                    if(dp[i] == 0) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                    else {
                        dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                    }
                }
                else if(i - coins[j] == 0) {
                    dp[i] = 1;
                }
                else {
                    continue;
                }
            }
        }
        if(dp[amount] == 0) {
            return -1;
        }
        else {
            return dp[amount];
        }
    }
}