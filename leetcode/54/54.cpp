class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        vector<int> ans;
        if(matrix.size() == 0)
            return ans;
        int left = 0, right = matrix[0].size() - 1;
        int top = 0, down = matrix.size() - 1;
        int row ,col;
        while(left <= right && top <= down) {
            for(row = top, col = left; col <= right; col++) 
                ans.push_back(matrix[row][col]);
            for(row = top + 1, col = right; row <= down; row++)
                ans.push_back(matrix[row][col]);
            if(top < down && left < right) {
                for(row = down, col = right - 1; col >= left; col--)
                    ans.push_back(matrix[row][col]);
                for(row = down - 1, col = left; row > top; row--)
                    ans.push_back(matrix[row][col]);
            }
            top++;
            down--;
            left++;
            right--;
        }
        return ans;
    }
};