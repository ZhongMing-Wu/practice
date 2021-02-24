class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        for(int i = 0; i < nums.length;) {
            if(nums[i] <= 0) {
                ++i;
                continue;
            }
            if(nums[nums[i] - 1] > 0) {
                int index = nums[i] - 1;
                nums[i] = nums[index];
                nums[index] = -1;                
            } else {
                --nums[nums[i] - 1];
                nums[i] = 0;
                ++i;
            }
        }   
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == -2) {
                ans.add(i + 1);
            }
        }
        return ans;
    }
}