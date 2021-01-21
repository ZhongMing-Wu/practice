class Solution {
    public int minOperations(int[] nums, int x) {
        int left = 0, right = 0, curSum = nums[0], numsSum = 0, ans = Integer.MAX_VALUE, n = nums.length;
        for(int i = 0; i < n; ++i) {
            numsSum += nums[i];
        }
        if(numsSum == x) {
            return n;
        }
        while(right < n - 1) {
            if(numsSum - curSum == x) {
                ans = Math.min(ans, n - (right - left + 1));
                right++;
                curSum += nums[right];
                continue;
            }
            if(numsSum - x > curSum) {
                right++;
                curSum += nums[right];
                continue;
            }
            if(numsSum - x < curSum) {
                curSum -= nums[left];
                left++;
            }
            if(left > right) {
                right = left;
                curSum = nums[right];
            }
        }

        if(x == numsSum - curSum) {
            ans = Math.min(ans, n - (right - left + 1)); 
        }
        return ans == Integer.MAX_VALUE? -1 : ans;
    }
}