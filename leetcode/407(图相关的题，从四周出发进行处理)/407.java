class Solution {
    boolean[][] visited;
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if(m == 1) {
            return 0;
        }
        if(n == 1) {
            return 0;
        }
        visited = new boolean[m][n];
        PriorityQueue<int[]> pQueue = new PriorityQueue<>((int[] a, int[] b)->{
            return heightMap[a[0]][a[1]] - heightMap[b[0]][b[1]];
        });
        for(int i = 0; i < n; ++i) {
            pQueue.offer(new int[] {0, i});
            visited[0][i] = true;
            pQueue.offer(new int[] {m - 1, i});
            visited[m - 1][i] = true;
        }
        for(int i = 1; i < m - 1; ++i) {
            pQueue.offer(new int[] {i, 0});
            visited[i][0] = true;
            pQueue.offer(new int[] {i, n - 1});
            visited[i][n - 1] =true;
        }

        int leftCount = m * n - 2 * m - 2 * n + 4;
        int ans = 0;
        while(leftCount > 0) {
            int[] minNode = pQueue.poll();
            int rowNum = minNode[0], colNum = minNode[1];
            if(rowNum > 0 && !visited[rowNum - 1][colNum]) {
                if(heightMap[rowNum][colNum] > heightMap[rowNum - 1][colNum]) {
                    ans += heightMap[rowNum][colNum] - heightMap[rowNum - 1][colNum];
                    heightMap[rowNum - 1][colNum] = heightMap[rowNum][colNum];
                }
                visited[rowNum - 1][colNum] = true;
                pQueue.offer(new int[] {rowNum - 1, colNum});
                --leftCount;
            }

            if(rowNum < m - 1 && !visited[rowNum + 1][colNum]) {
                if(heightMap[rowNum][colNum] > heightMap[rowNum + 1][colNum]) {
                    ans += heightMap[rowNum][colNum] - heightMap[rowNum + 1][colNum];
                    heightMap[rowNum + 1][colNum] = heightMap[rowNum][colNum];
                }
                visited[rowNum + 1][colNum] = true;
                pQueue.offer(new int[] {rowNum + 1, colNum});
                --leftCount;
            }

            if(colNum > 0 && !visited[rowNum][colNum - 1]) {
                if(heightMap[rowNum][colNum] > heightMap[rowNum][colNum - 1]) {
                    ans += heightMap[rowNum][colNum] - heightMap[rowNum][colNum - 1];
                    heightMap[rowNum][colNum - 1] = heightMap[rowNum][colNum];
                }
                visited[rowNum][colNum - 1] = true;
                pQueue.offer(new int[] {rowNum, colNum - 1});
                --leftCount;
            }

            if(colNum < n - 1 && !visited[rowNum][colNum + 1]) {
                if(heightMap[rowNum][colNum] > heightMap[rowNum][colNum + 1]) {
                    ans += heightMap[rowNum][colNum] - heightMap[rowNum][colNum + 1];
                    heightMap[rowNum][colNum + 1] = heightMap[rowNum][colNum];
                }
                visited[rowNum][colNum + 1] = true;
                pQueue.offer(new int[] {rowNum, colNum + 1});
                --leftCount;
            }
        }
        return ans;
    }
}