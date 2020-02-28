class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        List<Integer> dp = new ArrayList<>();
        int loc;
        for(int i = 0; i < nums.length; i++) {
            loc = BSearch(dp, nums[i]);
            if(loc > dp.size() - 1) {
                dp.add(nums[i]);
            }
            else {
                dp.set(loc, nums[i]);
            }
        }
        return dp.size();
    }

    private int BSearch(List<Integer> dp, int val) {
        if(dp.size() == 0) {
            return 1;
        }
        int left = 0, right = dp.size() - 1;
        while(left <= right) {
            if(dp.get((left + right) / 2) > val) {
                right = (left + right) / 2 - 1;
            }
            else if(dp.get((left + right) / 2) < val) {
                left = (left + right) / 2 + 1;
            }
            else {
                return (left + right) / 2;
            }
        }
        return left;
    }
}