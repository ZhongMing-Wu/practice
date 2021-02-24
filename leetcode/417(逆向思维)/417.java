class Solution {
    int[][] visited;
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) {
            return new ArrayList<>();
        }
        int n = matrix[0].length;

        int[][] accessPacific = new int[m][n];
        int[][] accessAtlantic = new int[m][n];
        visited = new int[m][n];
        for(int i = 0; i < n; ++i) {
            dfs(matrix, 0, i, accessPacific);
            dfs(matrix, m - 1, i, accessAtlantic);
        }

        for(int i = 0; i < m; ++i) {
            dfs(matrix, i, 0, accessPacific);
            dfs(matrix, i, n - 1, accessAtlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> oneAns;
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(accessPacific[i][j] == 1 && accessAtlantic[i][j] == 1) {
                    oneAns = new ArrayList<>();
                    oneAns.add(i);
                    oneAns.add(j);
                    ans.add(oneAns);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] matrix, int rowNum, int colNum, int[][] recordArray) {
        if(recordArray[rowNum][colNum] == 1) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        visited[rowNum][colNum] = 1;
        recordArray[rowNum][colNum] = 1;

        if(rowNum > 0 && visited[rowNum - 1][colNum] == 0 && matrix[rowNum - 1][colNum] >= matrix[rowNum][colNum]) {
            dfs(matrix, rowNum - 1, colNum, recordArray);
        }

        if(rowNum < m - 1 && visited[rowNum + 1][colNum] == 0 && matrix[rowNum + 1][colNum] >= matrix[rowNum][colNum]) {
            dfs(matrix, rowNum + 1, colNum, recordArray);
        }

        if(colNum > 0 && visited[rowNum][colNum - 1] == 0 && matrix[rowNum][colNum - 1] >= matrix[rowNum][colNum]) {
            dfs(matrix, rowNum, colNum - 1, recordArray);
        }

        if(colNum < n -1 && visited[rowNum][colNum + 1] == 0 && matrix[rowNum][colNum + 1] >= matrix[rowNum][colNum]) {
            dfs(matrix, rowNum, colNum + 1, recordArray);
        }

        visited[rowNum][colNum] = 0;
    }
}