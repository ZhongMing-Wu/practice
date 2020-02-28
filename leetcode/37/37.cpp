class Solution {
public:
	void solveSudoku(vector<vector<char>>& board) {
		rowVisited.resize(9);
		colVisited.resize(9);
		blockVisited.resize(9);
		finish = 0; // 用于表示是否得到最终结果
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					rowVisited[i].push_back(board[i][j]);
					colVisited[j].push_back(board[i][j]);
					blockVisited[i / 3 * 3 + j / 3].push_back(board[i][j]);
				}
			}
		}
		insertNumber(board, 0, 0);
	}

	void insertNumber(vector<vector<char>>& board, int row, int col) {
		vector<char>::iterator ptr;
		if (board[row][col] == '.') {
			for (char val = '1'; val <= '9'; val++) {
				if (finish) {
					break;
				}
				if (count(rowVisited[row].begin(), rowVisited[row].end(), val) == 0 &&
					count(colVisited[col].begin(), colVisited[col].end(), val) == 0 &&
					count(blockVisited[row / 3 * 3 + col / 3].begin(), blockVisited[row / 3 * 3 + col / 3].end(), val) == 0
					) {
					board[row][col] = val;
					rowVisited[row].push_back(val);
					colVisited[col].push_back(val);
					blockVisited[row / 3 * 3 + col / 3].push_back(val);
					if (row == 8 && col == 8) {
						finish = 1;
						return;
					}
					else {
						if (col == 8) {
							insertNumber(board, row + 1, 0);
						}
						else {
							insertNumber(board, row, col + 1);
						}
						if (!finish) {
							popVal(val, rowVisited[row]);
							popVal(val, colVisited[col]);
							popVal(val, blockVisited[row / 3 * 3 + col / 3]);
						}
					}
				}
			}
			if (finish) {
				return;
			}
			else {
				board[row][col] = '.';
				return;
			}
		}
		else {
			if (row == 8 && col == 8) {
				finish = 1;
				return;
			}
			if (col == 8) {
				insertNumber(board, row + 1, 0);
			}
			else {
				insertNumber(board, row, col + 1);
			}
			return;
		}
	}

	void popVal(int val, vector<char>& arr) {
		auto ptr = find(arr.begin(), arr.end(), val);
		if (ptr != arr.end()) {
			arr.erase(ptr);
		}
	}
private:
	vector<vector<char>> rowVisited;
	vector<vector<char>> colVisited;
	vector<vector<char>> blockVisited;
	int finish;
};