class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        HashMap<Integer, Integer> toCount = new HashMap<>();
        int ans = DFS(nums, 0, toCount, target);
        return ans;
    }

    private int DFS(int[] nums, int index, HashMap<Integer, Integer> toCount, int target) {
        if(index == nums.length) {
            if(target == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        if(toCount.containsKey(index * 100000 + target)) {
            return toCount.get(index * 100000 + target);
        }

        int curCount1 = DFS(nums, index + 1, toCount, target - nums[index]);
        int curCount2 = DFS(nums, index + 1, toCount, target + nums[index]);
        toCount.put(index * 100000 + target, curCount1 + curCount2);
        return curCount1 + curCount2;
    }
}