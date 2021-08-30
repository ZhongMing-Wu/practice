class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> criticals = new ArrayList<>();
        int[] nodeIds = new int[n];
        Arrays.fill(nodeIds, -1);
        Map<Integer, List<Integer>> toNeighbors = BuildGraph(connections);

        dfs(0, 0, -1, criticals, nodeIds, toNeighbors);
        return criticals;
    }

    public int dfs(int node, int nodeId, int parNode, List<List<Integer>> criticals, int[] nodeIds, Map<Integer, List<Integer>> toNeighbors) {
        nodeIds[node] = nodeId;

        List<Integer> neighbors = toNeighbors.getOrDefault(node, new ArrayList<>());
        for(int neighbor : neighbors) {
            if(neighbor == parNode) {
                continue;
            }
            if(nodeIds[neighbor] == -1) {
                nodeIds[node] = Math.min(nodeIds[node], dfs(neighbor, nodeId + 1, node, criticals, nodeIds, toNeighbors));
                continue;
            }
            nodeIds[node] = Math.min(nodeIds[node], nodeIds[neighbor]);
        }

        if(nodeId == nodeIds[node] && parNode != -1) {
            criticals.add(Arrays.asList(parNode, node));
        }

        return nodeIds[node];
    }
    public Map<Integer, List<Integer>> BuildGraph(List<List<Integer>> connections) {
        Map<Integer, List<Integer>> toNeighbors = new HashMap<>();
        for(List<Integer> connection : connections) {
            int a = connection.get(0), b = connection.get(1);
            List<Integer> list = null;
            if(!toNeighbors.containsKey(a)) {
                toNeighbors.put(a, new ArrayList<>());
            }
            list = toNeighbors.get(a);
            list.add(b);

            if(!toNeighbors.containsKey(b)) {
                toNeighbors.put(b, new ArrayList<>());
            }
            list = toNeighbors.get(b);
            list.add(a);
        }
        return toNeighbors;
    }
}