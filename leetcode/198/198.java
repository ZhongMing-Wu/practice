class Solution {
    public int rob(int[] nums) {
        int first = 0, second = 0;
        int ans = 0;
        for(int i = 1; i <= nums.length; i++) {
            ans = Math.max(first + nums[i - 1], second);
            first = second;
            second = ans;
        }
        return ans;
    }
}