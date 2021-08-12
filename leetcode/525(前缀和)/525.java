class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> toIndex = new HashMap<>();
        toIndex.put(0, -1);
        
        int count = 0, ans = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                count--;
            }
            if(nums[i] == 1) {
                count++;
            }
            if(toIndex.containsKey(count)) {
                ans = Math.max(ans, i - toIndex.get(count));
            } else {
                toIndex.put(count, i);
            }
        }
        return ans;
    }
}