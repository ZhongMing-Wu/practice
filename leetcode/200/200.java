class Solution {
    private int num;
    public int numIslands(char[][] grid) {
        num = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    DFS(grid, i, j);
                    num++;
                }
            }
        }
        return num;
    }

    void DFS(char[][] grid, int row, int col) {
        if(grid[row][col] == '0') {
            return;
        }
        grid[row][col] = '0';
        if(row + 1 < grid.length) {
            DFS(grid, row + 1, col);
        }

        if(col + 1 < grid[0].length) {
            DFS(grid, row, col + 1);
        }

        if(col - 1 >= 0) {
            DFS(grid, row, col - 1);
        }

        if(row - 1 >= 0) {
            DFS(grid, row - 1, col);
        }
    }
}