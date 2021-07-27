class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if(n <= 2) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        int curMax = height[0];
        for(int i = 0; i < n; ++i) {
            leftMax[i] = Math.max(curMax, height[i]);
            curMax = Math.max(height[i], curMax);
        }
        curMax = height[n - 1];
        for(int i = n - 1; i >= 0; --i) {
            rightMax[i] = Math.max(height[i], curMax);
            curMax = Math.max(curMax, height[i]);
        }

        int ans = 0;
        for(int i = 0; i < n; ++i) {
            int left = leftMax[i];
            int right = rightMax[i];
            if(height[i] < left && height[i] < right) {
                ans += Math.min(left, right) - height[i];
            }
        }
        return ans;
    }
}