class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int k, m = 0;
        while(dp[K][m] < N) {
            m++;
            for(k = 1; k <= K; k++) {
                dp[k][m] = 1 + dp[k][m - 1] + dp[k - 1][m - 1];
            }
        }
        return m;
    }
}