class Solution {
public:
    int numTrees(int n) {
        long long ans = 1;
        int i, j;
        int flag = 0;
        if(n % 2 == 1) {
            flag = 1;
            n++;    
        }
        for(i = n * 2, j = n; i > n; i = i - 2, j--) {
            ans *= i;
            ans *= (i - 1);
            ans /= j;
        }
        while(j > 1) {
            ans /= j;
            j--;
        }
        if(flag) {
            ans = ans * n / (( 2 * n - 1) * 2);
            n--;
        }
        ans /= (n + 1);
        return (int)ans;
    }
};