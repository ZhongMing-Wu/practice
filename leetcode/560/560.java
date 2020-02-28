class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        int Count = 0, sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sums.containsKey(sum - k) == true) {
                Count += sums.get(sum - k);
            }
            if(sums.containsKey(sum) == true) {
                sums.put(sum, sums.get(sum) + 1);
            }
            else {
                sums.put(sum, 1);
            }
        }
        return Count;
    }
}