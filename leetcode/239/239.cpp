class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0) {
            return nums;
        }
        if(k <= 1) {
            return nums;
        }
        final int LEN = nums.length;
        int[] left = new int[LEN];
        int[] right = new int[LEN];
        int partMax = Integer.MIN_VALUE;
        for(int i = 0; i < LEN; i++) {
            if(i % k == 0) {
                partMax = Integer.MIN_VALUE;
            }
            left[i] = Math.max(partMax, nums[i]);
            partMax = left[i];
        }
        partMax = Integer.MIN_VALUE;
        for(int i = LEN - 1; i >= 0; i--) {
            if(i % k == 0) {
                partMax = Integer.MIN_VALUE;
            }
            right[i] = Math.max(partMax, nums[i]);
            partMax = right[i];
        }
        int[] ans = new int[LEN - k + 1];
        int leftIndex = 0, rightIndex = k - 1, loc = 0;
        while(rightIndex < LEN) {
            ans[loc++] = Math.max(right[leftIndex], left[rightIndex]);
            rightIndex++;
            leftIndex++;
        }
        return ans;
    }
}