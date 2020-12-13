class NumMatrix {

    private int[][] cache;
    public NumMatrix(int[][] matrix) {
        if(0 == matrix.length) {
            return;
        }
        cache = new int[matrix.length][matrix[0].length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if(i == 0 && j == 0) {
                    cache[i][j] = matrix[i][j];
                    continue;
                }
                
                if(0 == i) {
                    cache[i][j] = cache[i][j - 1] + matrix[i][j];
                    continue;
                }

                if(0 == j) {
                    cache[i][j] = cache[i - 1][j] + matrix[i][j];
                    continue;
                }

                cache[i][j] = cache[i][j - 1] + cache[i - 1][j] + matrix[i][j] - cache[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(0 == row1 && 0 == col1) {
            return cache[row2][col2];
        }

        if(0 == row1) {
            return cache[row2][col2] - cache[row2][col1 - 1];
        }

        if(0 == col1) {
            return cache[row2][col2] - cache[row1 - 1][col2];
        }
        
        return cache[row2][col2] - cache[row2][col1 - 1] - cache[row1 - 1][col2] + cache[row1 - 1][col1 - 1];
    }
}