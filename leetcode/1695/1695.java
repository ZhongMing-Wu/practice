class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }

        int start = 0, index = 0, ans = Integer.MIN_VALUE;
        Map<Integer, Integer> toLoc = new HashMap<>(n);
        sum = 0;
        while(start < n) {
            if(index == n) {
                start = n;
                ans = Math.max(ans, sum);
                continue; 
            }
            if(!toLoc.containsKey(nums[index]) || toLoc.get(nums[index]) < start) {
                sum += nums[index];
                toLoc.put(nums[index], index);
                index++;
                continue;
            }
            start = toLoc.get(nums[index]) + 1;
            ans = Math.max(ans, sum);
            sum = preSum[index - 1] - preSum[start - 1];
        }
        return ans;
    }
}