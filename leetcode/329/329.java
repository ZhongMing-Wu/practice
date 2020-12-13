class Solution {
    int[][] memory;
    public int longestIncreasingPath(int[][] matrix) {
        int rowLen = matrix.length;
        if(rowLen == 0) {
            return 0;
        }
        int colLen = matrix[0].length;
        memory = new int[rowLen][colLen];
        int ans = 0;
        for(int i = 0; i < rowLen; i++) {
            for(int j = 0; j < colLen; j++) {
                ans = Math.max(ans, DFS(matrix, i, j));
            }
        }

        return ans;
    }

    private int DFS(int[][] matrix, int row, int col) {
        if(memory[row][col] != 0) {
            return memory[row][col];
        }

        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int depth = 0;
        if(row > 0 && matrix[row][col] < matrix[row - 1][col]) {
            depth = Math.max(DFS(matrix, row - 1, col), depth);
        }

        if(row < rowLen - 1 && matrix[row][col] < matrix[row + 1][col]) {
            depth = Math.max(DFS(matrix, row + 1, col), depth);
        }

        if(col > 0 && matrix[row][col] < matrix[row][col - 1]) {
            depth = Math.max(DFS(matrix, row, col - 1), depth);
        }

        if(col < colLen - 1 && matrix[row][col] < matrix[row][col + 1]) {
            depth = Math.max(DFS(matrix, row, col + 1), depth);
        }

        memory[row][col] = depth + 1;
        return memory[row][col];
    }
}