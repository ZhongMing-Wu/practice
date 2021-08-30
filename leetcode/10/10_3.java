class Solution {
    public boolean isMatch(String s, String p) {
        int len1 = s.length(), len2 = p.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        dp[0][0] = 1;
        for(int i = 0; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(i > 0 && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1))) {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                if(p.charAt(j - 1) == '*') {
                    if(i == 0 || (s.charAt(i - 1) != p.charAt(j - 2) && p.charAt(j - 2) != '.')) {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 1] | dp[i][j - 2] | dp[i - 1][j];
                    }
                }
            }
        }
        return dp[len1][len2] == 1 ? true : false;
    }
}