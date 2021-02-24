class Solution {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        if(left < right) {
            int index = partition(nums, left, right);
            quickSort(nums, left, index - 1);
            quickSort(nums, index + 1, right);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int val = nums[left];
        while(left < right) {
            while(left < right && nums[right] >= val) {
                --right;
            }
            nums[left] = nums[right];
            while(left < right && nums[left] <= val) {
                ++left;
            }
            nums[right] = nums[left];
        }
        nums[left] = val;
        return left;
    }
}