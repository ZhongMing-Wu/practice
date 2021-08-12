class Solution {
    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int left, int right) {
        if(left < right) {
            int mid = (right - left) / 2 + left;
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, mid, mid + 1, right);
        }
    }

    private void merge(int[] nums, int l1, int r1, int l2, int r2) {
        int[] tempArray = new int[r2 - l1 + 1];
        int left = l1, right = r2, tempIndex = 0;
        while(l1 <= r1 && l2 <= r2) {
            if(nums[l1] < nums[l2]) {
                tempArray[tempIndex++] = nums[l1++];
            } else {
                tempArray[tempIndex++] = nums[l2++];
            }
        }

        while(l1 <= r1) {
            tempArray[tempIndex++] = nums[l1++];
        }
        while(l2 <= r2) {
            tempArray[tempIndex++] = nums[l2++];
        }

        for(int i = 0;left <= right; ++left, ++i) {
            nums[left] = tempArray[i];
        }
    }
}