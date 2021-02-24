class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int len = 1; len <= n; ++len) {
            for(int start = 0; start <= n - len; ++start) {
                int end = start + len - 1;
                if(start == end) {
                    dp[start][end] = 1;
                } else if(start + 1 == end) {
                    dp[start][end] = s.charAt(start) == s.charAt(end) ? 2 : 1;
                } else {
                    if(s.charAt(start) == s.charAt(end)) {
                        dp[start][end] = dp[start + 1][end - 1] + 2;
                    } else {
                        dp[start][end] = Math.max(dp[start + 1][end], dp[start][end - 1]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}