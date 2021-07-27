class Solution {
    int[][] visited;
    int[][] operations;
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        visited = new int[m][n];
        operations = new int[][] {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] != '.' && doDFS(i, j, -1, -1, grid)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean doDFS(int rowNum, int colNum, int preRow, int preCol, char[][] grid) {
        if(visited[rowNum][colNum] != 0) {
            return true;
        }
        visited[rowNum][colNum] = 1;
        int m = grid.length, n = grid[0].length;
        for(int[] operation : operations) {
            int nextRow = rowNum + operation[0];
            int nextCol = colNum + operation[1];
            if(nextRow == preRow && nextCol == preCol) {
                continue;
            }
            if(nextRow < 0 || nextRow >= m) {
                continue;
            }
            if(nextCol < 0 || nextCol >= n) {
                continue;
            }
            if(grid[nextRow][nextCol] != grid[rowNum][colNum]) {
                continue;
            }
            if(doDFS(nextRow, nextCol, rowNum, colNum, grid)) {
                return true;
            }
        }
        visited[rowNum][colNum] = 0;
        grid[rowNum][colNum] = '.';
        return false;
    }
}