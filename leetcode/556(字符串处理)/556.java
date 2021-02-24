class Solution {
    public int nextGreaterElement(int n) {
        int[] nums = new int[10];
        int index = 9;
        while(n != 0) {
            nums[index--] = n % 10;
            n = n / 10;
        }
        ++index;
        int flag = 0;
        for(int i = 9; i >= index; --i) {
            int minIndex = -1;
            for(int j = i + 1; j < 10; ++j) {
                if(nums[j] > nums[i]) {
                    if(minIndex == -1) {
                        minIndex = j;
                        continue;
                    }
                    minIndex = nums[minIndex] > nums[j] ? j : minIndex;
                }
            }
            if(minIndex != -1) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
                Arrays.sort(nums, i + 1, 10);
                flag = 1;
                break;
            }
        }

        if(flag == 0) {
            return -1;
        }
        long ans = 0;
        for(int i = index; i < 10; ++i) {
            ans = ans * 10 + nums[i];
        }
        if(ans > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)ans;
    }
}