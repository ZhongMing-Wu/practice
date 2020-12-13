class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length, max = Integer.MIN_VALUE;
        for(int l = 0; l < cols; l++) {
            int[] rowSum = new int[rows];
            for(int r = l; r < cols; r++) {
                for(int i = 0; i < rows; i++) {
                    rowSum[i] = rowSum[i] + matrix[i][r];
                }
                max = Math.max(max, dpmax(rowSum, k));
                if(max == k) {
                    return k;
                }
            }
        }
        return max;
    }

    private int dpmax(int[] rowSum, int k) {
        int sum = 0, rows = rowSum.length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < rows; i++) {
            if(sum >= 0) {
                sum += rowSum[i];
            } else {
                sum = rowSum[i];
            }
            if(max < sum) {
                max = sum;
            }
        }

        if(max <= k) {
            return max;
        }

        max = Integer.MIN_VALUE;
        for(int l = 0; l < rows; l++) {
            sum = 0;
            for(int r = l; r < rows; r++) {
                sum += rowSum[r];
                if(sum > max && sum <= k) {
                    max = sum;
                }
                if(max == k) {
                    return k;
                }
            }
        }
        return max;
    }
}