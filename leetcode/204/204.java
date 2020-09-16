class Solution {
    public int countPrimes(int n) {
        int[] isPrime = new int[n + 1];
        
        for(int i = 2; i * i < n; i++) {
            if(0 == isPrime[i]) {
                int sum = i * i;
                while(sum < n) {
                    isPrime[sum] = 1;
                    sum += i;
                }
            }
        }

        int count = 0;
        for(int i = 2; i < n; i++) {
            if(0 == isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}