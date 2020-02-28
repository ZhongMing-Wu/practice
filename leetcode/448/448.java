class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int cur;
        for(int i = 0; i < nums.length; i++) {
            cur = nums[i] > 0 ? nums[i] : -nums[i];
            if(nums[cur - 1] > 0) {
                nums[cur - 1] = -nums[cur - 1];
            }
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ans.add(i + 1);
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }
        return ans;
    }
}