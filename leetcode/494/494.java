class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        if(S > 1000 || S < -1000) {
            return 0;
        }
        int[][] dp = new int[2001][2];
        int pre = 0, next = 1, temp, zero = 0;
        dp[nums[0] + 1000][pre] = 1;
        dp[1000 - nums[0]][pre] = 1;
        if(nums[0] == 0) {
            zero++;
        }
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == 0) {
                zero++;
                continue;
            }
            for(int j = -1000; j <= 1000; j++) {
                if(j + nums[i] <= 1000) {
                    dp[j + 1000][next] = dp[j + 1000 + nums[i]][pre];
                }
                else {
                    dp[j + 1000][next] = 0;
                }
                if(j - nums[i] >= -1000) {
                    dp[j + 1000][next] += dp[j - nums[i] + 1000][pre];
                }
            }
            temp = next;
            next = pre;
            pre = temp;
        }
        return dp[S + 1000][pre] * (1 << zero);
    }
}