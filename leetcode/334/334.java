class Solution {
    public boolean increasingTriplet(int[] nums) {
        int[] dp = new int[2];
        dp[0] = dp[1] = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < dp[0]) {
                dp[0] = nums[i];
            } else if(nums[i] > dp[0] && nums[i] < dp[1]) {
                dp[1] = nums[i];
            } else if(nums[i] > dp[1]) {
                return true;
            }
        }
        return false;
    }
}