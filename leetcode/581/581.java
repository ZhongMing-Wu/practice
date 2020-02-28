class Solution {
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length <= 1) {
            return 0;
        }
        int left = 0, right = nums.length - 1;
        while(left + 1 < nums.length && nums[left] <= nums[left + 1]) {
            left++;
        }
        while(right - 1 >= 0 && nums[right] >= nums[right - 1]) {
            right--;
        }
        if(left > right) {
            return 0;
        }
        int maxVal = nums[left], minVal = maxVal;
        for(int i = left + 1; i <= right; i++) {
            if(maxVal < nums[i]) {
                maxVal = nums[i];
            }
            if(minVal > nums[i]) {
                minVal = nums[i];
            }
        }
        while(left >= 0) {
            if(nums[left] > minVal) {
                left--;
            }
            else {
                break;
            }
        }
        while(right < nums.length) {
            if(nums[right] < maxVal) {
                right++;
            }
            else {
                break;
            }
        }
        return right - left - 1;
    }
}