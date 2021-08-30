class Solution {
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for(int stick : sticks) {
            pQueue.add(stick);
        }

        int totalCost = 0;
        while(pQueue.size() > 1) {
            int stick1 = pQueue.poll();
            int stick2 = pQueue.poll();
            totalCost += stick1 + stick2;
            pQueue.add(stick1 + stick2);
        }
        return totalCost;
    }
}