class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        if(!matrix.size()) {
            return 0;
        }
        int colLength = matrix[0].size();
        vector<int> heights, left, right;
        heights.resize(colLength);
        left.resize(colLength);
        right.resize(colLength);
        int maxVal = 0;
        int currentLeft , currentRight;
        for(int i = 0; i < matrix.size(); i++) {
            currentLeft = -1;
            currentRight = findNextZero(matrix, i ,0);
            for(int j = 0; j < colLength; j++) {
                if(matrix[i][j] == '0') {
                    currentRight = findNextZero(matrix, i, j + 1);
                    heights[j] = 0;
                    left[j] = currentLeft;
                    right[j] = currentRight;
                    currentLeft = j;
                }
                else {
                    if(i == 0 || heights[j] == 0) {
                        heights[j] = 1;
                        left[j] = currentLeft;
                        right[j] = currentRight;
                        maxVal = max(maxVal, right[j] - left[j] - 1);
                    }
                    else {
                        heights[j] = heights[j] + 1;
                        left[j] = max(currentLeft, left[j]);
                        right[j] = min(currentRight, right[j]);
                        maxVal = max(maxVal, (right[j] - left[j] - 1) * heights[j]);
                    }
                }
            }
        } 
        return maxVal;
    }

    int findNextZero(vector<vector<char>> matrix, int i, int start) {
        while(start < matrix[0].size()) {
            if(matrix[i][start] == '0') {
                return start;
            }
            start++;
        }
        return start;
    }
};