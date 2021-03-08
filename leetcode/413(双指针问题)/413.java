class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums.length < 3) {
            return 0;
        }
        int ans = 0, left = 0, right = 1, dis = 0;
        while(right < nums.length) {
            if(right - left == 1) {
                dis = nums[right] - nums[left];
                ++right;
                continue;
            }
            if(nums[right] - nums[right - 1] != dis) {
                ans += (right - left - 1) * (right - left - 2) / 2;
                left = right - 1;
                continue;
            }
            ++right;
        }
        ans += (right - left - 1) * (right - left - 2) / 2;
        return ans;
    }
}