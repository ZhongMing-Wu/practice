class Solution {
public:
    bool exist(vector<vector<char>>& board, string word) {
        if(!word.size())
            return true;
        if(!board.size())
            return false;
        bool flag = false;
        for(int rowNum = 0; rowNum < board.size(); rowNum++) {
            for(int colNum = 0; colNum < board[0].size(); colNum++) {
                flag = find(rowNum, colNum, board, word, 0);
                if(flag) {
                    break;
                }
            }
            if(flag) {
                break;
            }
        }
        return flag;
    }
    bool find(int rowNum, int colNum, vector<vector<char>>& board, string word, int nextIndex) {
        if(board[rowNum][colNum] == word[nextIndex]) {
            if(nextIndex == word.size() - 1) {
                return true;
            }
            else {
                board[rowNum][colNum] = '*';
                if(rowNum > 0 && board[rowNum - 1][colNum] != '*') {
                    if(find(rowNum - 1, colNum, board, word, nextIndex + 1)) {
                        return true;
                    }
                }

                if(rowNum < board.size() - 1 && board[rowNum + 1][colNum] != '*') {
                    if(find(rowNum + 1, colNum, board, word, nextIndex + 1)) {
                        return true;
                    }
                }

                if(colNum > 0 && board[rowNum][colNum - 1] != '*') {
                    if(find(rowNum, colNum - 1, board, word, nextIndex + 1)) {
                        return true;
                    }
                }

                if(colNum < board[0].size() - 1 && board[rowNum][colNum + 1] != '*') {
                    if(find(rowNum, colNum + 1, board, word, nextIndex + 1)) {
                        return true;
                    }
                }
                board[rowNum][colNum] = word[nextIndex];
                return false;
            }
        }
        else {
            return false;
        }
    }
};