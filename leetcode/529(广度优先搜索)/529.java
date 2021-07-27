class Solution {
    int[][] operations;
    boolean[][] visited;
    Queue<int[]> queue;
    public char[][] updateBoard(char[][] board, int[] click) {
        operations = new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
        }; 
        int m = board.length, n = board[0].length;
        visited = new boolean[m][n];
        queue = new LinkedList<>();
        queue.offer(click);
        visited[click[0]][click[1]] = true;
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            doJudge(node[0], node[1], board);
        } 
        return board;
    }

    private void doJudge(int rowNum, int colNum, char[][] board) {
        if(board[rowNum][colNum] == 'M') {
            board[rowNum][colNum] = 'X';
            return;
        }
        int m = board.length, n = board[0].length;
        List<int[]> surround = new ArrayList<>(8);
        int mineCount = 0;
        for(int[] operation : operations) {
            int nextRow = rowNum + operation[0];
            int nextCol = colNum + operation[1];
            if(nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                continue;
            }
            if(board[nextRow][nextCol] == 'M') {
                ++mineCount;
            }
            if(board[nextRow][nextCol] == 'E' && !visited[nextRow][nextCol]) {
                surround.add(new int[] {nextRow, nextCol});
            }
        }
        if(mineCount != 0) {
            board[rowNum][colNum] = (char)(mineCount + 48);
            return;
        }
        for(int[] node : surround) {
            visited[node[0]][node[1]] = true;
            queue.offer(node);
        }
        board[rowNum][colNum] = 'B';
    }
}