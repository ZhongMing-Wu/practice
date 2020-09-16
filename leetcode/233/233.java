class Solution {
    public int countDigitOne(int n) {
        int total = 0;
        for(long i = 1; i <= n;) {
            long j = i * 10;
            total += (n / j) * i + Math.min(Math.max((n % j - i + 1), 0), i);     
            i *= 10;
        }
        return total;
    }
}