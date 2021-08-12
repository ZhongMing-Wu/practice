class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }

        if(sum < target || (sum - target) % 2 != 0) {
            return 0;
        }
        
        int substructionSum = (sum - target) / 2;
        int[] dp = new int[substructionSum + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++) {
            for(int j = substructionSum; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[substructionSum];
    }
}