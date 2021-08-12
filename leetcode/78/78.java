class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for(int i = 0; i <= (int)Math.pow(2, len) - 1; i++) {
            addOneAns(nums, i, ans);
        }
        return ans;
    }

    private void addOneAns(int[] nums, int status, List<List<Integer>> ans) {
        List<Integer> list = new ArrayList<>();
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            if(((status >>> i) & 1) == 1) {
                list.add(nums[len - i - 1]);
            }
        }
        ans.add(list);
    }
}