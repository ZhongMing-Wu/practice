class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if(n == 0) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 9;
        int ans = 10;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * (10 - i + 1);
            ans += dp[i];
        }
        return ans;
    }
}