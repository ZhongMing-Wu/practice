class Solution {
    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        for(int i = 0; i < n; ++i) {
            int k = i + 2;
            for(int j = i + 1; j < n && nums[i] != 0; ++j) {
                while(k < n && nums[i] + nums[j] > nums[k]) {
                    ++k;
                }
                count += k - j -1;
            }
        }
        return count;
    }
}s