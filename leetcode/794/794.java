class Solution {
    public boolean validTicTacToe(String[] board) {
        int countX = 0, countO = 0, len = board.length;
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                if(board[i].charAt(j) == 'X') {
                    countX++;
                }
                if(board[i].charAt(j) == 'O') {
                    countO++;
                }
            }
        }
        if(countX != countO && countX != countO + 1) {
            return false;
        }

        if(win(board, 'X') && countX != countO + 1) {
            return false;
        }
        if(win(board, 'O') && countX != countO) {
            return false;
        }
        return true;
    }

    public boolean win(String[] board, char target) {
        int winCount = 0, len = board.length, flag;
        for(int i = 0; i < len; i++) {
            flag = 1;
            for(int j = 0; j < len; j++) {
                if(board[i].charAt(j) != target) {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) {
                winCount++;
            }
        }

        for(int i = 0;  i< len; i++) {
            flag = 1;
            for(int j = 0; j < len; j++) {
                if(board[j].charAt(i) != target) {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) {
                winCount++;
            }
        }
        flag = 1;
        for(int i = 0; i < len; i++) {
            if(board[i].charAt(i) != target) {
                flag = 0;
                break;
            }
        }
        if(flag == 1) {
            winCount++;
        }
        flag = 1;
        for(int i = 0; i < len; i++) {
            if(board[i].charAt(len - i - 1) != target) {
                flag = 0;
                break;
            }
        }
        if(flag == 1) {
            winCount++;
        }
        return winCount == 1 ? true : false;
    }
}