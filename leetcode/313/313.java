class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        int[] dp = new int[primes.length];  // 存储某元素在 uglyNums 中的下标

        int currentCount = 1;
        while(currentCount < n) {
            int minValIndex = findMinVal(dp, primes, uglyNums);
            if(uglyNums[(dp[minValIndex])] * primes[minValIndex] != uglyNums[(currentCount - 1)]) {
                uglyNums[currentCount] = uglyNums[dp[minValIndex]] * primes[minValIndex];
                currentCount++;
            }
            dp[minValIndex]++;
        }

        return uglyNums[currentCount - 1];
    }

    int findMinVal(int[] dp, int[] primes, int[] uglyNums) {
        int minValIndex = 0;
        for(int i = 1; i < dp.length; i++) {
            if(uglyNums[dp[minValIndex]] * primes[minValIndex] > uglyNums[dp[i]] * primes[i]) {
                minValIndex = i;
            }
        }
        return minValIndex;
    }
}