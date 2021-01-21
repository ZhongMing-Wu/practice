class Solution {
    public int countVowelStrings(int n) {
        int[][] dp = new int[5][n + 1];
        initDp(dp);
        for(int i = 2; i <= n; ++i) {
            calculateNth(i, dp);
        }
        int ans = 0;
        for(int i = 0; i < 5; ++i) {
            ans += dp[i][n];
        }
        return ans;
    }

    private void calculateNth(int n, int[][] dp) {
        for(int i = 0; i < 5; ++i) {
            for(int j = i; j < 5; ++j) {
                dp[i][n] += dp[j][n - 1];
            }
        }
    }

    private void initDp(int[][] dp) {
        dp[0][1] = 1;
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[3][1] = 1;
        dp[4][1] = 1;
    } 
}