class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue1 = new LinkedList<>();
        Queue<int[]> queue2 = new LinkedList<>();

        int m = grid.length, n = grid[0].length;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 2) {
                    int[] node = new int[] {i, j};
                    queue1.offer(node);
                }
            }
        }

        int day = 0;
        while(!queue1.isEmpty()) {
            while(!queue1.isEmpty()) {
                int[] node = queue1.poll();
                int rowNum = node[0], colNum = node[1];
                if(rowNum > 0 && grid[rowNum - 1][colNum] == 1) {
                    grid[rowNum - 1][colNum] = 2;
                    queue2.offer(new int[]{rowNum - 1, colNum});
                }
                if(rowNum < m - 1 && grid[rowNum + 1][colNum] == 1) {
                    grid[rowNum + 1][colNum] = 2;
                    queue2.offer(new int[]{rowNum + 1, colNum});
                }
                if(colNum > 0 && grid[rowNum][colNum - 1] == 1) {
                    grid[rowNum][colNum - 1] = 2;
                    queue2.offer(new int[]{rowNum, colNum - 1});
                }
                if(colNum < n - 1 && grid[rowNum][colNum + 1] == 1) {
                    grid[rowNum][colNum + 1] = 2;
                    queue2.offer(new int[]{rowNum, colNum + 1});
                }
            }
            if(queue2.isEmpty()) {
                break;
            }

            while(!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
            ++day;
        }

        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(grid[i][j] == 1) {
                    return -1;
                }
            }
        }
        return day;
    }
}