class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        if(nums.length == 0) {
            return true;
        }
        for(int val : nums) {
            sum += val;
        }
        if(sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        int[][] dp = new int[nums.length][sum + 1];
        for(int i = 0; i <= sum; i++) {   //这是列
            for(int j = 0; j < nums.length; j++) {   //这是行
                if(j == 0) {
                    if(nums[j] == i) {
                        dp[j][i] = 1;
                    }
                    else {
                        dp[j][i] = 0;
                    }
                }
                else {
                    if(nums[j] == i) {
                        dp[j][i] = 1;
                    }
                    else if(nums[j] > i) {
                        dp[j][i] = dp[j - 1][i];
                    }
                    else {
                        dp[j][i] = dp[j - 1][i - nums[j]] | dp[j - 1][i];
                    }
                }
            }
        }
        return dp[nums.length - 1][sum] == 1 ? true : false;
    }
}