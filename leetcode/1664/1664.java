class Solution {
    public int waysToMakeFair(int[] nums) {
        int evenVal = 0, oddVal = 0, ans = 0;
        if(nums.length == 1) {
            return 1;
        }
        for(int i = 1; i < nums.length; i++) {
            if((i - 1) % 2 == 0) {
                evenVal += nums[i];
            } else {
                oddVal += nums[i];
            }
        }      
        if(evenVal == oddVal) {
            ans++;
        }

        for(int i = 1; i < nums.length; i++) {
            if((i - 1) % 2 == 0) {
                evenVal += nums[i - 1];
                evenVal -= nums[i];
            } else {
                oddVal += nums[i - 1];
                oddVal -= nums[i];
            }

            if(evenVal == oddVal) {
                ans++;
            }
        }
        return ans;
    }
}