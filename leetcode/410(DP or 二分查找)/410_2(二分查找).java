class Solution {
    public int splitArray(int[] nums, int m) {
        int right = 0, left = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; ++i) {
            left = Math.min(nums[i], left);
            right += nums[i];
        }   
        while(left < right) {
            int middle = (left + right) / 2;
            if(check(nums, middle, m)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int sum, int m) {
        int cnt = 0, curSum = 0;
        for(int i = 0; i < nums.length;) {
            if(nums[i] > sum) {
                return false;
            }
            if(curSum + nums[i] <= sum) {
                curSum += nums[i];
                ++i;
            } else {
                ++cnt;
                curSum = 0;
            }
        }
        ++cnt;
        return cnt <= m ? true : false;
    }
}