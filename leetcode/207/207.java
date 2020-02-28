class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
            int [] visit = new int[numCourses];
            Map<Integer, ArrayList<Integer>> adjacency = new HashMap<Integer, ArrayList<Integer>>();
            for(int i = 0; i < numCourses; i++) {
                adjacency.put(i, new ArrayList<Integer>());
            }

            for(int [] prerequisite : prerequisites) {
                adjacency.get(prerequisite[0]).add(prerequisite[1]);
            }

            for(int i = 0; i < numCourses; i++) {
                    if(visit[i] == 0) {
                        if(!DFS(i, visit, adjacency)) {
                            return false;
                    }
                }
            }
            return true;
    }

    boolean DFS(int num, int [] visit, Map<Integer, ArrayList<Integer>> adjacency) {
        if(visit[num] == 1) {
            return false;
        }
        else if(visit[num] == -1) {
            return true;
        }
        else {
            visit[num] = 1;
            for(int nextNode : adjacency.get(num)) {
                if(!DFS(nextNode, visit, adjacency)) {
                    return false;
                }
            }
            visit[num] = -1;
            return true;
        }
    }
}