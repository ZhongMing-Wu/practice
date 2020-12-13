class Solution {
    public int integerBreak(int n) {
        int ans = 0, temp;
        for(int i = 2; i <= n; i++) {
            temp = customizePow(n / i, i - n % i) * customizePow(n / i + 1, n % i);
            if(ans > temp) {
                break;
            }
            ans =temp;
        }
        return ans;
    }

    private int customizePow(int a, int b) {
        int ans = 1;
        for(int i = 0; i < b; i++) {
            ans *= a;
        }
        return ans;
    }
}