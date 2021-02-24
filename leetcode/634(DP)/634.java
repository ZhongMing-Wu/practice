class Solution {
    public int findDerangement(int n) {
        if(n < 2) {
            return 0;
        }
        long first = 0, second = 1;
        for(int i = 3; i <= n; ++i) {
            long temp = (i - 1) * (first + second) % 1000000007;
            first = second;
            second = temp;
        }
        return (int)second;
    }
}