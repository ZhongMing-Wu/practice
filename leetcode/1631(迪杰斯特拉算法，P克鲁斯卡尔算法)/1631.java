class Solution {
    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        int[][] visited = new int[row][col];
        int[][] dis = new int[row][col];

        for(int i = 0; i < row; ++i) {
            for(int j = 0; j < col; ++j) {
                dis[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pQueue.offer(new int[] {0, 0, 0});

        int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!pQueue.isEmpty()) {
            int[] node = pQueue.poll();
            int rowNum = node[0];
            int colNum = node[1];
            if(visited[rowNum][colNum] == 1) {
                continue;
            }
            dis[rowNum][colNum] = node[2];
            visited[rowNum][colNum] = 1;

            for(int[] dir : dirs) {
                if(rowNum + dir[0] >= 0 && rowNum + dir[0] < row && colNum + dir[1] >= 0 && colNum + dir[1] < col) {
                    int nextRow = rowNum + dir[0], nextCol = colNum + dir[1];
                    if(visited[nextRow][nextCol] == 0) {
                        int[] newNode = new int[3];
                        newNode[0] = nextRow;
                        newNode[1] = nextCol;
                        newNode[2] = Math.max(Math.abs(heights[nextRow][nextCol] - heights[rowNum][colNum]), dis[rowNum][colNum]);
                        pQueue.offer(newNode);
                    }
                }
            }
        }

        return dis[row - 1][col - 1];
    }
}