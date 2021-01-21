class Solution {
    public int minMoves(int[] nums, int limit) {
        int[] diff = new int[limit * 2 + 2];
        int[] ans = new int[limit * 2 + 1];
        int n = nums.length;
        for(int i = 0; i < n / 2; i++) {
            int A = nums[i], B = nums[n - i -1];
            int l = 2, r = limit * 2;
            diff[l] += 2;
            diff[r + 1] -= 2;

            l = 1 + Math.min(A, B);
            r = limit + Math.max(A, B);
            diff[l] -= 1;
            diff[r + 1] += 1;

            l = r = A + B;
            diff[l] -= 1;
            diff[r + 1] += 1;
        }

        int minAns = diff[2];
        int[] dp = new int[limit * 2 + 1];
        for(int i = 2; i <= limit * 2; i++) {
            dp[i] = dp[i - 1] + diff[i];
            minAns = Math.min(minAns, dp[i]);
        }
        return minAns;
    }
}