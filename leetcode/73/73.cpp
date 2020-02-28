class Solution {
public:
    void setZeroes(vector<vector<int>>& matrix) {
        for(int i = 0; i < matrix.size(); i++) {
            for(int j = 0; j < matrix[0].size(); j++) {
                if(matrix[i][j] == 0) {
                    tackle(matrix, i ,j);
                }
            }
        }
        for(int i = 0; i < matrix.size(); i++) {
            for(int j = 0; j < matrix[0].size(); j++){
                if(matrix[i][j] == -1000) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    void tackle(vector<vector<int>>& toMatrix, int rowNum, int colNum) {
        for(int i = 0; i < toMatrix[0].size(); i++) {
            if(toMatrix[rowNum][i])
                toMatrix[rowNum][i] = -1000;
        }
        for(int i = 0; i < toMatrix.size(); i++) {
            if(toMatrix[i][colNum])
                toMatrix[i][colNum] = -1000;
        }
    }
};