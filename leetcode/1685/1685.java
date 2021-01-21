class Solution {
    public int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 1; i < n; i++) {
            result[0] += nums[i] - nums[0];
        }

        for(int i = 1; i < n; i++) {
            int up = nums[i] - nums[i - 1];
            result[i] = result[i - 1] + i * up - (n - i) * up;
        }
        return result;
    }
}