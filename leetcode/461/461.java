class Solution {
    public int hammingDistance(int x, int y) {
        int m = x ^ y;
        int n = 1;
        int count = 0;
        while(n <= m) {
            if((n & m) == n) {
                count++;
            }
            m = m >> 1;
        }
        return count;
    }
}