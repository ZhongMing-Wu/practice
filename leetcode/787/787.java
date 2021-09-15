class Solution {
    final int inf = 1000000000;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] pre = new int[n];
        Arrays.fill(pre, inf);
        pre[src] = 0;
        int ans = inf;
        for(int i = 1; i < k + 2; i++) {
            int[] cur = new int[n];
            Arrays.fill(cur, inf);
            for(int[] flight : flights) {
                cur[flight[1]] = Math.min(cur[flight[1]], pre[flight[0]] + flight[2]);
            }
            ans = Math.min(ans, cur[dst]);
            pre = cur;
        }
        return ans == inf ? -1 : ans;
    }
}