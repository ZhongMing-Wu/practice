class Solution {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if(n <= 1) {
            return n;
        }
        int[] count = new int[n];
        int[] length = new int[n];
        Arrays.fill(count, 1);
        Arrays.fill(length, 1);
        for(int i = 1; i < n; ++i) {
            for(int j = 0; j < i; ++j) {
                if(nums[j] < nums[i]) {
                    if(length[j] >= length[i]) {
                        length[i] = length[j] + 1;
                        count[i] = count[j];
                    } else if(length[j] + 1 == length[i]) {
                        count[i] += count[j];
                    }
                }
            }
        }

        int maxLen = 0;
        for(int i = 0; i < n; ++i) {
            maxLen = Math.max(maxLen, length[i]);
        }
        
        int ans = 0;
        for(int i = 0; i < n; ++i) {
            if(length[i] == maxLen) {
                ans += count[i];
            }
        }
        return ans;
    }
}