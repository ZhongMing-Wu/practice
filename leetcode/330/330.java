class Solution {
    public int minPatches(int[] nums, int n) {
        int ans = 0, index = 0;
        long nextNum = 1;
        while(nextNum <= n) {
            if(index < nums.length && nextNum >= nums[index]) {
                nextNum += nums[index];
                index++;
            } else {
                nextNum += nextNum;
                ans++;
            }
        }

        return ans;
    }
}