class Solution {
    List<String> ans;
    Map<String, PriorityQueue<String>> toNext;
    public List<String> findItinerary(List<List<String>> tickets) {
        toNext = new HashMap<>(tickets.size());
        ans = new ArrayList<>(tickets.size());

        String from, to;
        for(List<String> list : tickets) {
            from = list.get(0);
            to = list.get(1);

            if(toNext.containsKey(from)) {
                toNext.get(from).add(to);
            } else {
                PriorityQueue<String> queue = new PriorityQueue<>();
                queue.add(to);
                toNext.put(from, queue);
            }
        }
        DFS("JFK");
        Collections.reverse(ans);
        return ans;
    }

    public void DFS(String curStr) {
        while(toNext.containsKey(curStr) && toNext.get(curStr).size() != 0) {
            DFS(toNext.get(curStr).poll());
        }
        ans.add(curStr);
    }
}