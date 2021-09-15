class Solution {
    public int numRollsToTarget(int d, int f, int target) {
        int[] dp = new int[target + 1];
        for(int i = 0; i < d; i++) {
            for(int j = target; j >= 1; j--) {
                if(i == 0) {
                    dp[j] = j > f ? 0 : 1;
                    continue;
                }
                int count = 0;
                for(int k = 1; k <= Math.min(j, f); k++) {
                    count += dp[j - k];
                    count %= 1000000007;
                }
                dp[j] = count % 1000000007;
            }
        }
        return dp[target];
    }
}