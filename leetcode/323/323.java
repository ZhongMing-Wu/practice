class Solution {
    public int countComponents(int n, int[][] edges) {
        int[] father = new int[n];
        for(int i = 0; i < n; i++) {
            father[i] = i;
        }

        for(int i = 0; i < edges.length; i++) {
            int f1 = findFather(father, edges[i][0]);
            int f2 = findFather(father, edges[i][1]);

            union(father, f1, f2);
        }

        int[] visited = new int[n];
        for(int i = 0; i < n; i++) {
            int index = findFather(father, i);
            visited[index] = 1;
        }

        int ans = 0;
        for(int val : visited) {
            ans += val;
        }
        return ans;
    }

    public void union(int[] father, int val1, int val2) {
        father[val2] = val1;
    }
    public int findFather(int[] father, int val) {
        int f = val;
        while(father[f] != f) {
            f = father[f];
        }

        int c = val;
        while(father[c] != f) {
            int temp = father[c];
            father[c] = f;
            c = temp;
        }
        return f;
    }
}