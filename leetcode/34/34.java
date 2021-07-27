class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) {
            return new int[] {-1, -1};
        }
        int[] ans = new int[2];
        ans[0] = doSearchTarget(nums, target, 0);
        ans[1] = doSearchTarget(nums, target, 1);
        return ans;
    }

    private int doSearchTarget(int[] nums, int target, int direction) {
        int left = 0, right = nums.length - 1;
        while(left < right) {
            int middle = (right - left) / 2 + left;
            if(nums[middle] < target) {
                left = middle + 1;
                continue;
            } 
            if(nums[middle] > target) {
                right = middle - 1;
                continue;
            }
            if(direction == 0) {
                right = middle;
            } else {
                if(nums[right] == target) {
                    return right;
                }
                left = middle;
                --right;
            }
        }
        if(nums[left] == target) {
            return left;
        }
        return -1;
    }
}