class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[nums.length - 1] = 1;
        int maxLen = 1;
        for(int i = nums.length - 2; i >= 0; i--) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if(dp[i] == 0) {
                dp[i] = 1;
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}