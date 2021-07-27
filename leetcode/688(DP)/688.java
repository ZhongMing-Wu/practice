class Solution {
    public double knightProbability(int N, int K, int r, int c) {
        if(K == 0) {
            return 1;
        }
        double[][] dp = new double[N][N];
        int[][] operates = new int[][] {
            {-2, 1}, {-2, -1},{2, 1}, {2, -1}, {1, -2}, {-1, -2}, {1, 2}, {-1, 2}
        };
        for(int k = 1; k <= K; ++k) {
            double[][] nextdp = new double[N][N];
            for(int i = 0; i < N; ++i) {
                for(int j = 0; j < N; ++j) {
                    for(int[] operate : operates) {
                        if(i + operate[0] >= 0 && i + operate[0] < N && j + operate[1] >= 0 && j + operate[1] < N) {
                            if(k == 1) {
                                nextdp[i][j] += 1.0 / 8;
                            } else {
                                nextdp[i][j] += dp[i + operate[0]][j + operate[1]] / 8;
                            }
                        }
                    }
                }
            }
            dp = nextdp;
        }
        return dp[r][c];
    }
}