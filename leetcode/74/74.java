class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int rowNum = 0, colLen = n - 1;
        while(rowNum < m && colLen >= 0) {
            if(matrix[rowNum][colLen] == target) {
                return true;
            }
            if(matrix[rowNum][colLen] > target) {
                --colLen;
            } else {
                ++rowNum;
            }
        }
        return false;
    }
}