class Solution {
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int[][] dp = new int[n][n];
        int modNum = 1000000000 + 7;
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if(S.charAt(i) != S.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                } else {
                    dp[i][j] = 2 * dp[i + 1][j - 1];
                    int l = i + 1, r = j - 1;
                    char c = S.charAt(i);
                    while(l < j && S.charAt(l) != c) {
                        l++;
                    }
                    while(r > i && S.charAt(r) != c) {
                        r--;
                    }
                    if(l > r) {
                        dp[i][j] += 2;
                    } else if(l == r) {
                        dp[i][j] += 1;
                    } else {
                        dp[i][j] -= dp[l + 1][r - 1];
                    }
                }
                dp[i][j] = dp[i][j] < 0? dp[i][j] + modNum : dp[i][j] % modNum;
            }
        }
        return dp[0][n - 1];
    }
}