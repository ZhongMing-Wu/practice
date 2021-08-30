class Solution {
    public int cutOffTree(List<List<Integer>> forest) {
        HashMap<Integer, int[]> toLoc = new HashMap<>();
        List<Integer> heights = new ArrayList<>();
        for(int i = 0; i < forest.size(); i++) {
            List<Integer> list = forest.get(i);
            for(int j = 0; j < list.size(); j++) {
                int height = list.get(j);
                if(height > 1) {
                    heights.add(height);
                    toLoc.put(height, new int[] {i, j});
                }
            }
        }

        heights.sort((Integer a, Integer b) -> {
            return a - b;
        });
        int totalDistance = 0;
        int[] targetLoc = new int[] {0, 0};
        for(int i = 0; i < heights.size(); i++) {
            int[] originLoc = targetLoc;
            targetLoc = toLoc.get(heights.get(i));
            int distance = dist(forest, originLoc[0], originLoc[1], targetLoc[0], targetLoc[1]);
            if(distance == -1) {
                return -1;
            }
            totalDistance += distance;
        }
        return totalDistance;
    }

    public int dist(List<List<Integer>> forest, int originRow, int originCol, int targetRow, int targetCol) {
        Queue<int[]> queue = new LinkedList<>();
        int rowLen = forest.size(), colLen = forest.get(0).size();
        boolean[][] visited = new boolean[rowLen][colLen];
        queue.offer(new int[] {originRow, originCol, 0});
        int[][] operation = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
         visited[originRow][originCol] = true;
        while(!queue.isEmpty()) {
            int[] loc = queue.poll();
            if(loc[0] == targetRow && loc[1] == targetCol) {
                return loc[2];
            }
            for(int i = 0; i < 4; i++) {
                int nextRow = loc[0] + operation[i][0];
                int nextCol = loc[1] + operation[i][1];
                if(nextRow >= 0 && nextRow < rowLen && nextCol >= 0 && nextCol < colLen && !visited[nextRow][nextCol] && forest.get(nextRow).get(nextCol) != 0) {
                    queue.offer(new int[] {nextRow, nextCol, loc[2] + 1});
                    visited[nextRow][nextCol] = true;
                }
            }
        }
        return -1;
    }
}