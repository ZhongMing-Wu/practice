class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        if(n == 0) {
            return ans;
        }
        Arrays.fill(ans, -1);
        LinkedList<Integer> dp = new LinkedList<>();
        int endIndex = -1, curIndex = 0;
        while(endIndex == -1 || curIndex != endIndex) {
            while(!dp.isEmpty() && nums[dp.peekLast()] < nums[curIndex]) {
                ans[dp.pollLast()] = nums[curIndex];
            }
            if(dp.isEmpty()) {
                endIndex = curIndex;
            }
            if(ans[curIndex] == -1) {
                dp.offerLast(curIndex);
            }
            curIndex = (curIndex + 1) % n;
        }
        int maxVal = nums[endIndex];
        while(!dp.isEmpty()) {
            curIndex = dp.pollLast();
            if(nums[curIndex] != nums[endIndex]) {
                ans[curIndex] = nums[endIndex];
            } else {
                ans[curIndex] = -1;
            }
        }
        return ans;
    }
}