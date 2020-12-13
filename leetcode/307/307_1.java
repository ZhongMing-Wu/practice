class NumArray {

    int[] dp;
    int[] initNums;
    public NumArray(int[] nums) {
        initNums = nums;
        dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            if(0 == i) {
                dp[i] = nums[i];
                continue;
            }
            dp[i] = dp[i - 1] + nums[i];
        }
    }
    
    public void update(int i, int val) {
        int gap = val - initNums[i];
        for(int j = i; j < dp.length; j++) {
            dp[j] += gap;
        }
        initNums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        if(i == 0) {
            return dp[j];
        }
        return dp[j] - dp[i - 1];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */