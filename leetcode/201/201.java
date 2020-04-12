class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int bit_loc = 0, numCount = n - m + 1, ans = 0, start = 0;
        while(bit_loc < 31) {
            int temp = 1 << bit_loc;
            if((m & temp) == temp && temp - start >= numCount) {
                ans += temp;
            }
            if((m & temp) == temp) {
                start += temp;
            }
            bit_loc++;
        }
        return ans;
    }
}