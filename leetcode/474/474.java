class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for(String str : strs) {
            int[] count = calculateZeroesAndOnes(str);
            for(int zeroes = m; zeroes >= count[0]; zeroes--) {
                for(int ones = n; ones >= count[1]; ones--) {
                    dp[zeroes][ones] = Math.max(dp[zeroes][ones], dp[zeroes - count[0]][ones - count[1]] + 1);
                }
            }
        }
        return dp[m][n];
    }
    private int[] calculateZeroesAndOnes(String str) {
        int[] count = new int[2];
        for(int i = 0; i < str.length(); i++) {
            if('0' == str.charAt(i)) {
                count[0]++;
            } else {
                count[1]++;
            }
        }
        return count;
    }
}