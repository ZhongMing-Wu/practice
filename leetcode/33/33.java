class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, len =nums.length;
        while(left <= right) {
            int mid = (right - left) / 2 + left;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] > target) {
                if(nums[mid] > nums[len - 1] && target <= nums[len - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if(nums[mid] < target) {
                if(nums[mid] <= nums[len - 1] && target > nums[len - 1]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}