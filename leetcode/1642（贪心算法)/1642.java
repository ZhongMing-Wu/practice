class Solution {
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        int n = heights.length, needBricksNum = 0;
        PriorityQueue<Integer> pQueue = new PriorityQueue<>(n, (a, b) -> b - a);
        for(int i = 1; i < n; ++i) {
            int heightGap = heights[i] - heights[i - 1];
            if(heightGap <= 0) {
                continue;
            }

            needBricksNum += heightGap;
            pQueue.offer(heightGap);

            if(needBricksNum > bricks && ladders > 0) {
                int maxHeightGap = pQueue.poll();
                --ladders;
                needBricksNum -= maxHeightGap;
                continue;
            }

            if(needBricksNum > bricks && ladders == 0) {
                return  i - 1;
            }
        }
        return n - 1;
    }
}