class Solution {
    Queue<int[]> queue;
    int[][] visited;
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length, n = rooms[0].length;
        queue = new LinkedList<>();
        visited = new int[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(rooms[i][j] == 0) {
                    int[] node = new int[2];
                    node[0] = i;
                    node[1] = j;
                    queue.offer(node);
                    visited[i][j] = 1;
                }
                if(rooms[i][j] == -1) {
                    visited[i][j] = 1;
                }
            }
        }
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int rowNum = node[0], colNum = node[1];
            if(rowNum > 0 && visited[rowNum - 1][colNum] == 0) {
                rooms[rowNum - 1][colNum] = rooms[rowNum][colNum] + 1;
                visited[rowNum - 1][colNum] = 1;
                queue.offer(new int[] {rowNum - 1, colNum});
            }

            if(rowNum < m - 1 && visited[rowNum + 1][colNum] == 0) {
                rooms[rowNum + 1][colNum] = rooms[rowNum][colNum] + 1;
                visited[rowNum + 1][colNum] = 1;
                queue.offer(new int[] {rowNum + 1, colNum});
            }

            if(colNum < n - 1 && visited[rowNum][colNum + 1] == 0) {
                rooms[rowNum][colNum + 1] = rooms[rowNum][colNum] + 1;
                visited[rowNum][colNum + 1] = 1;
                queue.offer(new int[] {rowNum, colNum + 1});
            }

            if(colNum > 0 && visited[rowNum][colNum - 1] == 0) {
                rooms[rowNum][colNum - 1] = rooms[rowNum][colNum] + 1;
                visited[rowNum][colNum - 1] = 1;
                queue.offer(new int[] {rowNum, colNum - 1});
            }
        }
    }
}