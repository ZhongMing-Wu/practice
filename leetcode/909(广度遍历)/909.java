class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] visited = new int[n * n + 1];
        int targetId = n * n, curSetp = 0, curStageCount = 1, nextStageCount = 0, flag = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = 1;
        while(!queue.isEmpty()) {
            int id = queue.poll();
            if(id == targetId) {
                flag = 1;
                break;
            }
            curStageCount--;
            for(int i = 1; i <= 6; i++) {
                int nextId = id + i;
                if(nextId > targetId) {
                    continue;
                }
                int[] loc = id2Loc(nextId, n);
                if(board[loc[0]][loc[1]] != -1) {
                    // visited[nextId] = 1;
                    nextId = board[loc[0]][loc[1]];
                }
                if(visited[nextId] == 1) {
                    continue;
                }
                queue.offer(nextId);
                visited[nextId] = 1;
                nextStageCount++;
            }
            if(curStageCount == 0) {
                curStageCount = nextStageCount;
                curSetp++;
                nextStageCount = 0;
            }
        }
        
        return flag == 0 ? -1 : curSetp;
    }

    private int[] id2Loc(int id, int n) {
        int row = (id - 1) / n;
        int col = (id - 1) % n;
        if(row % 2 == 1) {
            col = n - col - 1;
        }

        return new int[] {n - 1 - row, col};
    }
}