class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int row, col;
        while(!queue.isEmpty()) {
            int[] intermediateNode = queue.poll();
            if(intermediateNode[0] == destination[0] && intermediateNode[1] == destination[1]) {
                return true;
            }
            row = intermediateNode[0];
            col = intermediateNode[1];
            while(row - 1 >= 0 && maze[row - 1][col] == 0) {
                row--;
            }
            if(!visited[row][col]) {
                queue.offer(new int[] {row, col});
                visited[row][col] = true;
            }

            row = intermediateNode[0];
            col = intermediateNode[1];
            while(row + 1 < m && maze[row + 1][col] == 0) {
                row++;
            }
            if(!visited[row][col]) {
                queue.offer(new int[] {row, col});
                visited[row][col] = true;
            }

            row = intermediateNode[0];
            col = intermediateNode[1];
            while(col + 1 < n && maze[row][col + 1] == 0) {
                col++;
            }
            if(!visited[row][col]) {
                queue.offer(new int[] {row, col});
                visited[row][col] = true;
            }

            row = intermediateNode[0];
            col = intermediateNode[1];
            while(col - 1 >= 0 && maze[row][col - 1] == 0) {
                col--;
            }
            if(!visited[row][col]) {
                queue.offer(new int[] {row, col});
                visited[row][col] = true;
            }
        }
        return false;
    }
}