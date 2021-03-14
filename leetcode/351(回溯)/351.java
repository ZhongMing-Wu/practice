class Solution {
    int count;
    int[] visited;
    int [][]skip;
    public int numberOfPatterns(int m, int n) {
        skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[4][6] = skip[6][4] = skip[2][8] = skip[8][2] = 5;
        skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
        skip[7][9] = skip[9][7] = 8;
        count = 0;
        visited = new int[10];
        doDFS(1, m , n, 0);
        doDFS(2, m , n, 0);
        count *= 4;
        doDFS(5, m , n, 0);
        return count;
    }
    private void doDFS(int num, int m, int n, int curLen) {
        ++curLen;
        visited[num] = 1;
        if(curLen > n) {
            visited[num] = 0;
            return;
        }
        if(curLen >= m) {
            ++count;
        }

        for(int i = 1; i <= 9; ++i) {
            if(visited[i] == 0 && (skip[num][i] == 0 || visited[skip[num][i]] != 0)) {
                doDFS(i, m, n, curLen);
            }
        }
        visited[num] = 0;
    }
}