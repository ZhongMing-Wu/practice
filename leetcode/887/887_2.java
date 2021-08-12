class Solution {
    public int superEggDrop(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        for(int i = 1; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == 1) {
                    dp[i][j] = j;
                    continue;
                }
                int left = 1, right = j, mid = 0;
                while(left <= right) {
                    mid = (right - left) / 2 + left;
                    if(dp[i - 1][mid - 1] > dp[i][j - mid]) {
                        right = mid - 1;
                    }
                    if(dp[i - 1][mid - 1] < dp[i][j - mid]) {
                        left = mid + 1;
                    }
                    if(dp[i - 1][mid - 1] == dp[i][j - mid]) {
                        break;
                    }
                }
                dp[i][j] = Math.max(dp[i - 1][mid - 1], dp[i][j - mid]) + 1;
            }
        }
        return dp[k][n];
    }
}