class Solution {
    public int minimumMoves(int[] arr) {
        int len = arr.length;
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for(int i = 0; i < len - 1; i++) {
            if(arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
            } else {
                dp[i][i + 1] = 2;
            }
        }

        for(int i = 3; i <= len; i++) {
            for(int j = 0; j + i <= len; j++) {
                int left = j, right = j + i - 1;
                dp[left][right] = i;
                for(int k = left; k < right; k++) {
                    if(dp[left][k] + dp[k + 1][right] < dp[left][right]) {
                        dp[left][right] = dp[left][k] + dp[k + 1][right];
                    }
                }
                if(arr[left] == arr[right]) {
                    dp[left][right] = Math.min(dp[left][right], dp[left + 1][right - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}