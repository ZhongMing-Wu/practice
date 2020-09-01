class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        if(buildings.length == 0) {
            return ans;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for(int[] building : buildings) {
            pq.offer(new int[] {building[0], building[2]});
            pq.offer(new int[] {building[1], -building[2]});
        }
        LinkedList<Integer> heights = new LinkedList<>();
        Iterator<Integer> itr = null;
        List<Integer> oneAns = new ArrayList<>();
        oneAns.add(-1);
        oneAns.add(-1);
        int[] temp = null;
        int[] temp2 = null;
        while(!pq.isEmpty()) {
            temp = pq.poll();
            if(temp[1] > 0) {
                heights.offerLast(temp[1]);
            }
            else if(temp[1] < 0) {
                itr = heights.iterator();
                while(itr.hasNext()) {
                    if(itr.next() == -temp[1]) {
                        itr.remove();
                        break;
                    }
                }
            }

            temp2 = pq.peek();
            if(temp2 != null && temp[0] == temp2[0]) {
                continue;
            }
            int maxHeight = 0;
            itr = heights.iterator();
            while(itr.hasNext()) {
                maxHeight = Math.max(maxHeight, itr.next());
            }
            if(maxHeight != oneAns.get(1)) {
                oneAns.set(0, temp[0]);
                oneAns.set(1, maxHeight);
                ans.add(new ArrayList<>(oneAns));
            }
        }
        return ans;
    }
}