class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] dis = new int[m][n];
        for(int i = 0; i < m; i++) {
            Arrays.fill(dis[i], Integer.MAX_VALUE);
        }
        Queue<int[]> queue = new LinkedList<>();
        // 先遍历获取到所有的 0，并加入队列
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 0) {
                    queue.offer(new int[] {i, j, 0});
                    dis[i][j] = 0;
                }
            }
        }
        // 循环遍历队列，把访问到的元素加入队列
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            int row = node[0], col = node[1], nextDis = node[2] + 1;
            if(row < m - 1 && dis[row + 1][col] > nextDis) {
                dis[row + 1][col] = nextDis;
                queue.offer(new int[] {row + 1, col, nextDis});  
            }
            if(row > 0 && dis[row - 1][col] > nextDis) {
                dis[row - 1][col] = nextDis;
                queue.offer(new int[] {row - 1, col, nextDis});
            }
            if(col > 0 && dis[row][col - 1] > nextDis) {
                dis[row][col - 1] = nextDis;
                queue.offer(new int[] {row, col - 1, nextDis});
            }
            if(col < n - 1 && dis[row][col + 1] > nextDis) {
                dis[row][col + 1] = nextDis;
                queue.offer(new int[] {row, col + 1, nextDis});
            }
        }
        return dis;
    }
}