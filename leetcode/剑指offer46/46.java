class Solution {
    public int translateNum(int num) {
        String strNum = String.valueOf(num);
        int n = strNum.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for(int i = 1; i <= n; ++i) {
            if(i == 1) {
                dp[1] = 1;
                continue;
            } 
            dp[i] = dp[i - 1];
            String partNum = strNum.substring(i - 2, i);
            if(doJudge(partNum)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
    private boolean doJudge(String partNum) {
        int val = Integer.valueOf(partNum);
        if(val < 10) {
            return false;
        }
        return val > 25 ? false : true;
    }
}
