class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num + 1];
        int pre = 1;
        for(int i = 1; i <= num; i++) {
            if(i % 2 == 1) {
                ans[i] = ans[i - 1] + 1;
            }
            else {
                if(pre * 2 == i) {
                    ans[i] = 1;
                    pre = i;
                }
                else {
                    ans[i] = ans[i - pre] + 1;
                }
            }
        }
        return ans;
    }
}