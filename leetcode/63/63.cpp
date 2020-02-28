class Solution {
public:
    int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
        if(!obstacleGrid.size()) {
            return 0;
        }
        int rowLength = obstacleGrid.size();
        int colLength = obstacleGrid[0].size();
        vector<vector<long>> anses;
        anses.resize(rowLength);
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(i == 0 && j == 0) {
                    if(obstacleGrid[i][j]) {
                        anses[i].push_back(0);
                    }
                    else {
                        anses[i].push_back(1);
                    }
                }
                else if(i == 0) {
                    if(obstacleGrid[i][j]) {
                        anses[i].push_back(0);
                    }
                    else {
                        anses[i].push_back(anses[i][j - 1]);
                    }
                }
                else if(j == 0) {
                    if(obstacleGrid[i][j]) {
                        anses[i].push_back(0);
                    }
                    else {
                        anses[i].push_back(anses[i - 1][j]);
                    }
                }
                else {
                    if(obstacleGrid[i][j]) {
                        anses[i].push_back(0);
                    }
                    else {
                        anses[i].push_back(anses[i][j - 1] + anses[i - 1][j]);
                    }
                }
            }
        }
        return (int)anses[rowLength - 1][colLength - 1]; 
    }
};