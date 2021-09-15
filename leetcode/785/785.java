class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;  i < n; i++) {
            if(colors[i] != 0) {
                continue;
            }
            colors[i] = -1;
            queue.offer(i);
            while(!queue.isEmpty()) {
                int node = queue.poll();
                int[] neighbors = graph[node];
                int nextColor = -colors[node];
                for(int neighbor : neighbors) {
                    if(colors[neighbor] == 0) {
                        colors[neighbor] = nextColor;
                        queue.offer(neighbor);
                        continue;
                    }
                    if(colors[neighbor] != nextColor) {
                        return false;
                    } 
                }
            }
        }
        return true;
    }
}