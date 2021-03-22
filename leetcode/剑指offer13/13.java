class Solution {
    public int movingCount(int m, int n, int k) {
        boolean[][] canVisit = new boolean[m][n];
        canVisit[0][0] = true;
        int count = 1;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(i > 0 && canVisit[i - 1][j] && calBitSum(i) + calBitSum(j) <= k) {
                    ++count;
                    canVisit[i][j] = true;
                } else if(j > 0 && canVisit[i][j - 1] && calBitSum(i) + calBitSum(j) <= k) {
                    ++count;
                    canVisit[i][j] = true;
                }
            }
        }
        return count;
    }

    private int calBitSum(int num) {
        int sum = 0;
        while(num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}