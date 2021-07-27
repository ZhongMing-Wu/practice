class Solution {
    public int maximumSum(int[] arr) {
        int n = arr.length;
        if(n == 1) {
            return arr[0];
        }
        int[] dp = new int[2];
        dp[0] = arr[0];
        dp[1] = 0;
        int ans = Integer.MIN_VALUE;
        for(int i = 1; i < n; ++i) {
            int temp = dp[0];
            if(dp[0] <= 0) {
                dp[0] = arr[i];
            } else {
                dp[0] = arr[i] + dp[0];
            }

            dp[1] = Math.max(temp, dp[1] + arr[i]);
            ans = Math.max(ans, Math.max(dp[1], dp[0]));
        }
        return ans;
    }
}