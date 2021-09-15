class Solution {
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        HashMap<String, Integer> existed = new HashMap<>();
        int differCount = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    String path = findOneIsland(grid, visited, i, j);
                    if(!existed.containsKey(path)) {
                        differCount++;
                        existed.put(path, 1);
                    }
                }
            }
        }
        return differCount;
    }

    public String findOneIsland(int[][] grid, boolean[][] visited, int startRow, int startCol) {
        int m = grid.length, n = grid[0].length;
        StringBuilder path = new StringBuilder();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {startRow, startCol});
        visited[startRow][startCol] = true;
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int curRow = node[0], curCol = node[1];
            path.append(curRow - startRow).append(curCol - startCol);
            if(curRow > 0 && !visited[curRow - 1][curCol] && grid[curRow - 1][curCol] == 1) {
                queue.offer(new int[] {curRow - 1, curCol});
                visited[curRow - 1][curCol] = true;
            }

            if(curRow < m - 1 && !visited[curRow + 1][curCol] && grid[curRow + 1][curCol] == 1) {
                queue.offer(new int[] {curRow + 1, curCol});
                visited[curRow + 1][curCol] = true;
            }

            if(curCol < n - 1 && !visited[curRow][curCol + 1] && grid[curRow][curCol + 1] == 1) {
                queue.offer(new int[] {curRow, curCol + 1});
                visited[curRow][curCol + 1] = true;
            }

            if(curCol > 0 && !visited[curRow][curCol - 1] && grid[curRow][curCol - 1] == 1) {
                queue.offer(new int[] {curRow, curCol - 1});
                visited[curRow][curCol - 1] = true;
            }
        }
        return path.toString();
    }
}