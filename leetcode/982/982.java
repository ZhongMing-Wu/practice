class Solution {
    public int countTriplets(int[] A) {
        
        int upLimit = 0;
        for(int num1 : A) {
            for(int num2 : A) {
                upLimit = Math.max(upLimit, num1 & num2);
            }
        }
        
        int[] dp = new int[upLimit + 1];
        for(int num : A) {
            for(int i = 0; i <= upLimit; i++) {
                if((num & i) == 0) {
                    dp[i]++;
                } 
            }
        }
        
        int ans = 0;
        for(int num1 : A) {
            for(int num2 : A) {
                ans += dp[(num1 & num2)];
            }
        }
        return ans;
    }
}