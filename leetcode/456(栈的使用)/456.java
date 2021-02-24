class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] preMin = new int[n];
        int curMin = Integer.MAX_VALUE;
        for(int i = 0; i < n; ++i) {
            if(nums[i] > curMin) {
                preMin[i] = curMin;
            } else {
                preMin[i] = nums[i];
                curMin = nums[i];
            }
        }

        Deque<Integer> stack = new LinkedList<>();
        for(int i = n - 1; i >= 0; --i) {
            if(stack.isEmpty() || nums[i] <= stack.peekFirst()) {
                stack.push(nums[i]);
                continue;
            }
            while(!stack.isEmpty() && nums[i] > stack.peekFirst()) {
                int rightVal = stack.pop();
                if(rightVal > preMin[i]) {
                    return true;
                }
            }
            stack.push(nums[i]);
        }
        return false;
    }
}