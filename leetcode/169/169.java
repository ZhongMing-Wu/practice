class Solution {
    public int majorityElement(int[] nums) {
        int ans = nums[0], occurTime = 0;

        for(int num : nums) {
            if(ans == num) {
                occurTime++;
            }
            else {
                occurTime--;
                if(occurTime == 0) {
                    ans = num;
                    occurTime++;
                }
            }
        }
        return ans;
    }
}