class Solution {
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] nums) {
        int n = nums.length;
        for(int i = n / 2; i >= 0; --i) {
            adjustHeap(nums, i, n);
        }
        for(int i = n - 1; i >= 0; --i) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            adjustHeap(nums, 0, i);
        }
    }
    private void adjustHeap(int[] nums, int index, int len) {
        int val = nums[index];
        for(int i = index * 2; i < len; i *= 2) {
            if(i + 1 < len && nums[i] < nums[i + 1]) {
                ++i;
            }
            if(val >= nums[i]) {
                break;
            }
            nums[index] = nums[i];
            index = i;
        }
        nums[index] = val;
    }
}