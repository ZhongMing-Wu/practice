class Solution {
    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        if(len == 0) {
            return 0;
        }
        int[] dp = new int[len];
        PriorityQueue<Integer> queue = new PriorityQueue<>(len, (Integer a, Integer b) -> {
            return dp[b] - dp[a];
        });
        
        dp[0] = nums[0];
        queue.add(0);
        for(int i = 1; i < len; i++) {
            int maxIndex;
            while(true) {
                maxIndex = queue.peek();
                if(i - k > maxIndex) {
                    queue.poll();
                    continue;
                }
                dp[i] = dp[maxIndex] + nums[i];
                queue.add(i);
                break;
            }
        }
        return dp[len - 1];
    }
}