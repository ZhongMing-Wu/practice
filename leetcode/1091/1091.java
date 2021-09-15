class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if(grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        } 
        Queue<int[]> queue= new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        queue.offer(new int[] {0, 0, 1});
        visited[0][0] = true;
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            if(node[0] == n - 1 && node[1] == n - 1) {
                return node[2];
            }
            int row = node[0], col = node[1];
            if(row > 0) {
                if(!visited[row - 1][col] && grid[row - 1][col] == 0) {
                    visited[row - 1][col] = true;
                    queue.offer(new int[] {row - 1, col, node[2] + 1});
                }
                if(col > 0 && !visited[row - 1][col - 1] && grid[row - 1][col - 1] == 0) {
                    visited[row - 1][col - 1] = true;
                    queue.offer(new int[] {row - 1, col - 1, node[2] + 1});
                }
                if(col < n - 1 && !visited[row - 1][col + 1] && grid[row - 1][col + 1] == 0) {
                    visited[row - 1][col + 1] = true;
                    queue.offer(new int[] {row - 1, col + 1, node[2] + 1});
                }
            }
            if(col > 0 && !visited[row][col - 1] && grid[row][col - 1] == 0) {
                visited[row][col - 1] = true;
                queue.offer(new int[] {row, col - 1, node[2] + 1});
            }

            if(col < n - 1 && !visited[row][col + 1] && grid[row][col + 1] == 0) {
                visited[row][col + 1] = true;
                queue.offer(new int[] {row, col + 1, node[2] + 1});
            }

            if(row < n - 1) {
                if(!visited[row + 1][col] && grid[row + 1][col] == 0) {
                    visited[row + 1][col] = true;
                    queue.offer(new int[] {row + 1, col, node[2] + 1});
                }
                if(col > 0 && !visited[row + 1][col - 1] && grid[row + 1][col - 1] == 0) {
                    visited[row + 1][col - 1] = true;
                    queue.offer(new int[] {row + 1, col - 1, node[2] + 1});
                }
                if(col < n - 1 && !visited[row + 1][col + 1] && grid[row + 1][col + 1] == 0) {
                    visited[row + 1][col + 1] = true;
                    queue.offer(new int[] {row + 1, col + 1, node[2] + 1});
                }
            }
        }
        return -1;
    }
}