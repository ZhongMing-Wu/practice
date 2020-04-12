class Solution {
    List<Integer> ans;
    int[] visited;
    Map<Integer, List<Integer>> toNextNodes;
    int[] preNodeCount;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ans = new ArrayList<Integer>();
        preNodeCount = new int[numCourses];
        toNextNodes = new HashMap<Integer, List<Integer>>();
        List<Integer> temp;
        for(int i = 0; i < prerequisites.length; i++) {
            preNodeCount[prerequisites[i][0]]++;
            if(toNextNodes.containsKey(prerequisites[i][1])) {
                temp = toNextNodes.get(prerequisites[i][1]);
                temp.add(prerequisites[i][0]);
            }
            else {
                temp = new ArrayList<Integer>();
                temp.add(prerequisites[i][0]);
                toNextNodes.put(prerequisites[i][1], temp);
            }
        }
        visited = new int[numCourses];
        int flag = 0;
        for(int i = 0; i < numCourses; i++) {
            if(visited[i] == 0 && preNodeCount[i] == 0) {
                flag = DFS(i);
                if(flag == 1) {
                    return new int[0];
                }
            }
        }
        if(ans.size() != numCourses) {
            return new int[0];
        }
        int[] arrayAns = new int[numCourses];
        for(int i = ans.size() - 1, j = 0; i >= 0; i--, j++) {
            arrayAns[j] = ans.get(i);
        }
        return arrayAns;
    }

    int DFS(int val) {
        visited[val] = 1;
        List<Integer> nextNodes = null;
        if(toNextNodes.containsKey(val)) {
            nextNodes = toNextNodes.get(val);
        }
        int nextNode, flag = 0;
        if(nextNodes != null) {
            for(int i = 0; i < nextNodes.size(); i++) {
                nextNode = nextNodes.get(i);
                if(visited[nextNode] == 0) {
                    flag = DFS(nextNode);
                }
                else if(visited[nextNode] == 1) {
                    return 1;
                }
                if(flag == 1) {
                    return 1;
                }
            }
        }
        visited[val] = 2;
        ans.add(val);
        return 0;
    }
}