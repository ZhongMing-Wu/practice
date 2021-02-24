class Solution {
    public int maximumSwap(int num) {
        int[] nums = new int[9];
        int tempNum = num, index = 8;
        while(tempNum != 0) {
            nums[index] = tempNum % 10;
            --index;
            tempNum /= 10;
        }
        ++index;
        int[] rightMax = new int[9];
        int curMaxIndex = 8;
        for(int i = 8; i>= index; --i) {
            if(nums[i] > nums[curMaxIndex]) {
                rightMax[i] = i;
                curMaxIndex = i;
            } else {
                rightMax[i] = curMaxIndex;
            }
        }
        for(int i = index; i < 9; ++i) {
            if(rightMax[i] != i && nums[i] != nums[rightMax[i]]) {
                int temp = nums[i];
                nums[i] = nums[rightMax[i]];
                nums[rightMax[i]] = temp;
                break;
            }
        }

        int ans = 0;
        for(int i = index; i < 9; ++i) {
            ans = ans * 10 + nums[i];
        }
        return ans;
    }
}