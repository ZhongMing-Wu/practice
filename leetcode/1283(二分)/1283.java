class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1, right = Integer.MIN_VALUE;
        for(int num : nums) {
            right = Math.max(right, num);
        }
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(doCheck(nums, threshold, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean doCheck(int[] nums, int threshold, int divisor) {
        int sum = 0;
        for(int num : nums) {
            sum += (num - 1) / divisor + 1;
        }
        return sum <= threshold;
    }
}