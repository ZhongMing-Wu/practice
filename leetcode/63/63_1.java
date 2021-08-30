class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }
        int rowLen = obstacleGrid.length, colLen = obstacleGrid[0].length;
        int[] dp = new int[colLen];
        dp[0] = 1;
        for(int j = 1; j < colLen; j++) {
            if(obstacleGrid[0][j] == 1) {
                dp[j] = 0;
                continue;
            }
            dp[j] = dp[j - 1];
        }
        for(int i = 1; i < rowLen; i++) {
            for(int j = 0; j < colLen; j++) {
                if(obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                if(j == 0) {
                    dp[j] = dp[j];
                    continue;
                }
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[colLen - 1];
    }
}