class Solution {
    public int concatenatedBinary(int n) {
        int leftMove = 0, modSum = 1000000007;
        long ans = 0;
        for(int i = 1; i <= n; i++) {
            if((i & (i - 1)) == 0) {
                leftMove++;
            }
            ans = ((ans << leftMove) + i) % modSum;
        }
        return (int)ans;
    }
}