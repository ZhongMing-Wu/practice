class TicTacToe {
    private int[][] board;
    private int target;
    private HashMap<Integer, Integer> verticalCount, horizonCount;
    private int[][] players;
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        board = new int[n][n];
        target = n;
        verticalCount = new HashMap<>();
        horizonCount = new HashMap<>();
        players = new int[3][2];
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        board[row][col] = player;
        horizonCount.put(row * 10 + player, horizonCount.getOrDefault(row * 10 + player, 0) + 1);
        if(horizonCount.get(row * 10 + player) == target) {
            return player;
        }
        verticalCount.put(col * 10 + player, verticalCount.getOrDefault(col * 10 + player, 0) + 1);
        if(verticalCount.get(col * 10 + player) == target) {
            return player;
        }

        if(row == col) {
            players[player][0]++;
            if(players[player][0] == target) {
                return player;
            }
        }
        if(row + col == target - 1) {
            players[player][1]++;
            if(players[player][1] == target) {
                return player;
            }
        }
        return 0;
    }
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */