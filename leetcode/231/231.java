class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0) {
            return false;
        }

        int val = n, count = 0;
        while(val > 0) {
            count += (val & 1);
            val = val >> 1;
        }
        return count == 1 ? true : false;
    }
}