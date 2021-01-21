class Solution {
    private int[][] mask_span, score_outer;
    private int[][][][] dp;
    private int[] nx_inner, wx_inner, score_inner;
    private int upper;
    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        init(n);
        for(int mask = 0; mask < upper; ++mask) {
            for(int mask_temp = mask, i = 0; i < n; ++i) {
                mask_span[mask][i] = mask_temp % 3;
                mask_temp /= 3;
            }
            for(int i = 0; i < n; ++i) {
                if(mask_span[mask][i] == 1) {
                    score_inner[mask] += 120;
                    ++nx_inner[mask];
                }
                if(mask_span[mask][i] == 2) {
                    score_inner[mask] += 40;
                    ++wx_inner[mask];
                }
            }

            for(int i = 0; i < n; i++) {
                if(i - 1 >= 0) {
                    score_inner[mask] += calc(mask_span[mask][i - 1], mask_span[mask][i]);
                }
            }
        }
        for(int mask1 = 0; mask1 < upper; ++mask1) {
            for(int mask2 = 0; mask2 < upper; ++mask2) {
               for(int i = 0; i < n; i++) {
                   score_outer[mask1][mask2] += calc(mask_span[mask1][i], mask_span[mask2][i]);
               } 
            }
        }
        return dfs(0, 0, introvertsCount, extrovertsCount, m);
    }

    private int dfs(int lastMask, int curRow, int nx, int wx, int m) {
        if(curRow == m || nx + wx == 0) {
            return 0;
        }

        if(dp[lastMask][curRow][nx][wx] != -1) {
            return dp[lastMask][curRow][nx][wx];
        }

        int best = 0;
        for(int mask = 0; mask < upper; ++mask) {
            if(nx_inner[mask] > nx || wx_inner[mask] > wx) {
                continue;
            }
            int score = score_inner[mask] + score_outer[lastMask][mask];
            best = Math.max(best, score + dfs(mask, curRow + 1, nx - nx_inner[mask], wx - wx_inner[mask], m));
        }

        return dp[lastMask][curRow][nx][wx] = best;
    }

    private void init(int col) {
        upper = (int)Math.pow(3, col);
        mask_span = new int[729][6];
        score_outer = new int[729][729];
        dp = new int[729][6][7][7];
        for(int i = 0; i < 729; i++) {
            for(int j = 0; j < 6; j++) {
                for(int m = 0; m < 7; m++) {
                    for(int n = 0; n < 7; n++) {
                        dp[i][j][m][n] = -1;
                    }
                }
            }
        }
        nx_inner = new int[729];
        wx_inner = new int[729];
        score_inner = new int[729];
    }

    private int calc(int x, int y) {
        if(x == 0 || y == 0) {
            return 0;
        }
        if(x == 1 && y == 1) {
            return -60;
        }
        if(x == 2 && y == 2) {
            return 40;
        }
        return -10;
    }
}