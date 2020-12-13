class Solution {
    public int superPow(int a, int[] b) {
        if(a == 1) {
            return 1;
        }
        if(a > 1337) {
            a = a % 1337;
        }
        return DfsCalculate(a, b, b.length);
    }

    private int DfsCalculate(int a, int[] b, int validLen) {
        if(validLen == 1) {
            int ans = myPow(a, b[validLen - 1]);
            return ans;
        }
        int ans = myPow(a, b[validLen - 1]);

        ans = (ans * myPow(DfsCalculate(a, b, validLen - 1), 10)) % 1337; 
        return ans;
    }

    private int myPow(int a, int k) {
        int ans = 1;
        for(int i = 0; i < k; i++) {
            ans = (ans * a) % 1337;
        }
        return ans;
    }
}