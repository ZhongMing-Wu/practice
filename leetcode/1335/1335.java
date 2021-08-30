class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int jobCount = jobDifficulty.length;
        if(jobCount < d) {
            return -1;
        }
        int[][] dp = new int[jobCount + 1][d + 1];
        int[] prefixMaxDifficulty = new int[jobCount];
        for(int i = 0; i < jobCount; i++) {
            if(i == 0) {
                prefixMaxDifficulty[i] = jobDifficulty[i];
            } else {
                prefixMaxDifficulty[i] = Math.max(prefixMaxDifficulty[i - 1], jobDifficulty[i]);
            }
        }
        for(int i = 1; i <= jobCount; i++) {
            int day = Math.min(i, d);
            for(int j = 1; j <= day; j++) {
                if(j == 1) {
                    dp[i][j] = prefixMaxDifficulty[i - 1];
                    continue;
                }
                int k = 1, curMaxDifficulty = jobDifficulty[i - k];
                while(i - k >= j - 1) {
                    if(dp[i][j] == 0) {
                        dp[i][j] = Integer.MAX_VALUE;
                    }
                    dp[i][j] = Math.min(dp[i - k][j - 1] + curMaxDifficulty, dp[i][j]);
                    k++;
                    if(i - k >= 0) {
                        curMaxDifficulty = Math.max(curMaxDifficulty, jobDifficulty[i - k]);
                    }
                }
            }
        }
        return dp[jobCount][d];
    }
}