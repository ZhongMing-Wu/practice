class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int left, right, middle;
        for(int k = 1; k <= K; k++) {
            for(int n = 1; n <= N; n++) {
                if(k == 1) {
                    dp[k][n] = n;
                }
                else {
                    dp[k][n] = Integer.MAX_VALUE;
                    left = 1;
                    right = n;
                    while(left <= right) {
                        middle = ( left + right) / 2;
                        if(dp[k - 1][middle - 1] < dp[k][n - middle]) {
                            left = middle + 1;
                        }
                        else if(dp[k - 1][middle - 1] > dp[k][n - middle]) {
                            right = middle - 1;
                        }
                        else {
                            dp[k][n] = dp[k - 1][middle - 1] + 1;
                            break;
                        }
                        dp[k][n] = Math.min(dp[k][n], Math.max(dp[k - 1][middle - 1], dp[k][n - middle]) + 1);
                    }
                }
            }
        }
        return dp[K][N];
    }
}