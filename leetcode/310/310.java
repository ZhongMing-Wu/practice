class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> ans = new ArrayList<>();
        if(n == 1) {
            ans.add(0);
            return ans;
        }

        Map<Integer, List<Integer>> toNeighborNode = new HashMap<>(n);
        List<Integer> neighborNode;
        for(int i = 0; i < n; i++) {
            neighborNode = new ArrayList<>();
            toNeighborNode.put(i, neighborNode);
        }
        int[] degree = new int[n];

        for(int i = 0; i < edges.length; i++) {
            degree[edges[i][0]]++;
            degree[edges[i][1]]++;
            toNeighborNode.get(edges[i][0]).add(edges[i][1]);
            toNeighborNode.get(edges[i][1]).add(edges[i][0]);
        }

        int count = n;
        int[] notValid = new int[n];
        while(true) {
            if(count <= 2) {
                  break;  
            }

            List<Integer> oneDegreeNum = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                if(1 == degree[i]) {
                    oneDegreeNum.add(i);
                }
            }

            for(int i = 0; i < oneDegreeNum.size(); i++) {
                int temp = oneDegreeNum.get(i);
                neighborNode = toNeighborNode.get(temp);
                for(int j = 0; j < neighborNode.size(); j++) {
                    if(0 == notValid[neighborNode.get(j)]) {
                        degree[neighborNode.get(j)]--;
                    }
                }
                degree[temp]--;
                count--;
                notValid[temp] = 1;
            }
        }

        for(int i = 0; i < n; i++) {
            if(0 == notValid[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}