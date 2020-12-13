class Solution {
    public void wiggleSort(int[] nums) {
        if(nums.length < 2) {
            return;
        }
        
        int loc = 0, len = nums.length, end, left = 0, right = len - 1;
        end = len % 2 == 0 ? len / 2 : len / 2 + 1;
        while(loc != end) {
            loc = quickSort(nums, left, right);
            if(loc < end) {
                left = loc + 1;
            } else if(loc > end) {
                right = loc - 1;
            }
        }
        loc--;

        int index = loc, middleVal = nums[loc];
        for(int i = loc; i >= 0; i--) {
            if(nums[i] != middleVal) {
                nums[index--] = nums[i];
            }
        }
        for(;index >= 0; index--) {
            nums[index] = middleVal;
        }

        index = loc + 1;
        for(int i = loc + 1; i < len; i++) {
            if(nums[i] != middleVal) {
                nums[index++] = nums[i];
            }
        }
        for(;index < len; index++) {
            nums[index] = middleVal;
        }

        int[] temp = new int[len];
        index = 0;
        for(int i = 0, j = loc + 1; i <= loc && j < len; i++, j++) {
            temp[index++] = nums[i];
            temp[index++] = nums[j];
        }

        if(index == len - 1) {
            temp[index] = nums[loc];
        }

        for(int i = 0; i < len; i++) {
            nums[i] = temp[i];
        }
    }

    private int quickSort(int[] nums, int left, int right) {
        int pivot = nums[left];

        while(left < right) {
            while(left < right && nums[right] > pivot) {
                right--;
            }
            nums[left] = nums[right];
            while(left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }

        nums[left] = pivot;
        return left;
    }
}