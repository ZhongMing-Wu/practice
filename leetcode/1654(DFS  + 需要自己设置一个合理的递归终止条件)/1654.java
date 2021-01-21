class Solution {
    int ans = Integer.MAX_VALUE;
    int[] visited;
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        visited = new int[6002];
        for(int i = 0; i < forbidden.length; ++i) {
            visited[forbidden[i]] = 1;
        }  
        dfs(x, 0, 0, 0, a, b);
        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        return ans;
    }

    private void dfs(int x, int curLoc, int steps, int from, int a, int b) {
        visited[curLoc] = 1;
        if(x == curLoc) {
            ans = Math.min(ans, steps);
            return;
        }
        
        if(curLoc + a <= 6001 && visited[curLoc + a] != 1) {
            dfs(x, curLoc + a, steps + 1, 0, a, b);
        }

        if(curLoc - b >= 0 && visited[curLoc - b] != 1 && from == 0) {
            dfs(x, curLoc - b, steps + 1, 1, a, b);
        }
        // 如果当前位置是前一个位置后退之后的位置，那么从该位置只能前进不能后退，
        // 所以递归只能验证从此位置开始下一步为向前时，是否能够到达指定位置，
        // 但是没有验证从此位置开始下一步为后退时，是否能够到达指定位置，
        // 所以需要重置，但是如果允许当前位置向前和向后，则不需要重置。
        if(from == 1) {
            visited[curLoc] = 0;
        }
    }
}