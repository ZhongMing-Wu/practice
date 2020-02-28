class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<string> oneAns;
        string temp = "";
        for(int i = 0; i < n; i++) {
            temp += '.';
        }
        for(int i = 0; i < n; i++) {
            oneAns.push_back(temp);
        }
        findOneAns(oneAns, 0, n);
        return ans;
    }

    void findOneAns(vector<string>& oneAns, int rowNum, int n) {
        for(int colNum = 0; colNum < n; colNum++) {
            if(rows[rowNum] == 0 && cols[colNum] == 0 
            && forwardDirecion[colNum + rowNum] == 0
            && backDirection[colNum - rowNum] == 0) {
                oneAns[rowNum][colNum] = 'Q';
                rows[rowNum] = 1;
                cols[colNum] = 1;
                forwardDirecion[colNum + rowNum] = 1;
                backDirection[colNum - rowNum] = 1;
                if(rowNum == n - 1) {
                    ans.push_back(oneAns);
                }
                else {
                    findOneAns(oneAns, rowNum + 1, n);
                }
                oneAns[rowNum][colNum] = '.';
                rows[rowNum] = 0;
                cols[colNum] = 0;
                forwardDirecion[colNum + rowNum] = 0;
                backDirection[colNum - rowNum] = 0;
            }
        }
    }
private:
    map<int, int> rows;
    map<int, int> cols;
    map<int, int> forwardDirecion;  //用于存储形如"/"的位置是否已使用
    map<int, int> backDirection;    //用于存储形如"\"的位置是否已使用
    vector<vector<string>> ans;
};