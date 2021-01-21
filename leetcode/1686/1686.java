class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        int[][] dp = new int[n][3];

        for(int i = 0; i < aliceValues.length; i++) {
            dp[i][0] = aliceValues[i] + bobValues[i];
            dp[i][1] = aliceValues[i];
            dp[i][2] = bobValues[i];
        }
        Arrays.sort(dp, (int[] a1, int[] a2) -> a2[0] - a1[0]);

        int aliceSum = 0, bobSum = 0;
        for(int i = 0; i < n; i++) {
            if(i % 2 == 0) {
                aliceSum += dp[i][1];
            } else {
                bobSum += dp[i][2];
            }
        }
        if(aliceSum > bobSum) {
            return 1;
        } else if(aliceSum < bobSum) {
            return -1;
        }

        return 0;
    }
}