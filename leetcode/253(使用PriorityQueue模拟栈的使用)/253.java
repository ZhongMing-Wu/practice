class Solution {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });

        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        int maxUseConference = 0;
        for(int i = 0; i < intervals.length; ++i) {
            while(!pQueue.isEmpty() && pQueue.peek() <= intervals[i][0]) {
                pQueue.poll();
            }
            pQueue.offer(intervals[i][1]);
            maxUseConference = Math.max(maxUseConference, pQueue.size());
        }
        return maxUseConference;
    }
}