class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 2][n + 2];
        int ans = -1;
        for(int len = 2; len <= n; len++) {
            for(int i = 1; i <= n - len + 1; i++) {
                int left = i, right = i + len - 1, minVal = Integer.MAX_VALUE;
                for(int j = (left + right) / 2; j <= right; j++) {
                    
                    minVal = Math.min(minVal, j + Math.max(dp[left][j - 1], dp[j + 1][right]));
                }
                dp[left][right] = minVal;
            }
        }
        return dp[1][n];
    }
}