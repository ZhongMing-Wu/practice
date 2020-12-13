class Solution {
    public int removeBoxes(int[] boxes) {
        int[][][] dp = new int[100][100][100];
        return calculate(boxes, dp, 0, boxes.length - 1, 0);
    }

    private int calculate(int[] boxes, int[][][] dp, int l, int r, int k) {
        if(l > r) {
            return 0;
        }

        if(dp[l][r][k] != 0) {
            return dp[l][r][k];
        }

        while(l < r && boxes[r - 1] == boxes[r]) {
            k++;
            r--;
        }

        dp[l][r][k] = calculate(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);

        for(int i = r- 1; i >= l; i--) {
            if(boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculate(boxes, dp, l, i, k + 1) + calculate(boxes, dp, i + 1, r- 1, 0));
            }
        }
        return dp[l][r][k];
    }
}