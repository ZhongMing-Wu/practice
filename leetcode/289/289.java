class Solution {
    public void gameOfLife(int[][] board) {
        if(0 == board.length) {
            return;
        }
        int rowLen = board.length;
        int colLen = board[0].length;
        int liveCount;

        for(int i = 0; i < rowLen; i++) {
            for(int j = 0; j < colLen; j++) {
                liveCount = 0;
                if(i > 0) {
                    if(board[i - 1][j] == 1 || board[i - 1][j] == 3) {
                        liveCount++;
                    }
                    if(j > 0) {
                        if(board[i - 1][j - 1] == 1 || board[i - 1][j - 1] == 3) {
                            liveCount++;
                        }
                    } 
                    if(j < colLen - 1) {
                        if(board[i - 1][j + 1] == 1 || board[i - 1][j + 1] == 3) {
                            liveCount++;
                        }
                    } 
                }

                if(i < rowLen - 1) {
                    if(board[i + 1][j] == 1 || board[i + 1][j] == 3) {
                        liveCount++;
                    }
                    if(j > 0) {
                        if(board[i + 1][j - 1] == 1 || board[i + 1][j - 1] == 3) {
                            liveCount++;
                        }
                    } 
                    if(j < colLen - 1) {
                        if(board[i + 1][j + 1] == 1 || board[i + 1][j + 1] == 3) {
                            liveCount++;
                        }
                    } 
                }

                if(j > 0) {
                    if(board[i][j - 1] == 1 || board[i][j - 1] == 3) {
                        liveCount++;
                    }
                }

                if(j < colLen - 1) {
                    if(board[i][j + 1] == 1 || board[i][j + 1] == 3) {
                        liveCount++;
                    }
                }

                if(liveCount < 2 || liveCount > 3) {
                    board[i][j] = board[i][j] == 0? 0 : 3;
                } else if(liveCount == 3) {
                    board[i][j] = board[i][j] == 1? 1 : 2;
                }
            }
        }
        
        for(int i = 0; i < rowLen; i++) {
            for(int j = 0; j < colLen; j++) {
                if(board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if(board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }
}