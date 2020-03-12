class Solution {
    public int findKthNumber(int n, int k) {
        int cur = 0, totalCount;
        long prefix = 1;
        while(cur < k) {
            totalCount = Count(prefix, n);
            if(totalCount + cur >= k) {
                prefix *= 10;
                cur++;
            }
            else {
                cur += totalCount;
                prefix += 1;
            }
        }
        prefix /= 10;
        return (int)prefix;
    }
    int Count(long prefix, int n) {
        long cur = prefix;
        long next = cur + 1;
        int totalCount = 0;
        while(cur <= n) {
            totalCount += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return totalCount;
    }
}