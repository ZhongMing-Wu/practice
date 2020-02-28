class Solution {
public:
    int minPathSum(vector<vector<int>>& grid) {
        int rowLength = grid.size();
        if(0 == rowLength)
            return 0;
        int colLength = grid[0].size();
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(i == 0) {
                    if(j == 0)
                        continue;
                    else {
                        grid[i][j] = grid[i][j - 1] + grid[i][j];
                    }
                }
                else if(j == 0) {
                    grid[i][j] = grid[i - 1][j] + grid[i][j];
                }
                else {
                    grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                }
            }
        }
        return grid[rowLength - 1][colLength - 1];
    }
};