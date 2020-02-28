class Solution {
public:
    int minDistance(string word1, string word2) {
        int rowLength = word1.size() + 1;
        int colLength = word2.size() + 1;
        vector<vector<int>> ans;
        ans.resize(rowLength);
        for(int i = 0; i < rowLength; i++) {
            ans[i].resize(colLength);
        }
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(i == 0) {
                    ans[i][j] = j;
                }
                else if(j == 0) {
                    ans[i][j] = i;
                }
                else {
                    if(word1[i - 1] == word2[j - 1]) {
                        ans[i][j] = 1 + min( min(ans[i - 1][j], ans[i][j - 1]), ans[i - 1][j - 1] - 1);
                    }
                    else {
                        ans[i][j] = 1 + min( min(ans[i - 1][j], ans[i][j - 1]), ans[i - 1][j - 1]);
                    }
                }
            }
        }
        return ans[word1.size()][word2.size()];
    }
};