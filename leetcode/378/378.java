class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        if(matrix.length == 0) {
            return 0;
        }
        int n = matrix.length;

        int left = matrix[0][0], right = matrix[n - 1][n - 1];
        while(left < right) {
            int middle = (left + right) / 2;
            if(checkNum(matrix, middle, n, k)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    private boolean checkNum(int[][] matrix, int middle, int n, int k) {
        int num = 0;
        int i = n - 1, j = 0;
        while(i >= 0 && j < n) {
            if(matrix[i][j] <= middle) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}