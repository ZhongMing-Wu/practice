class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] results = new int[n];
        int start = 0;
        Deque<Integer> stack = new LinkedList<>();
        for(String log : logs) {
            String[] info = log.split(":");
            int id = Integer.valueOf(info[0]);
            int tag = info[1].equals("start") ? 1 : 2;
            int time = Integer.valueOf(info[2]);

            if(tag == 1) {
                if(!stack.isEmpty()) {
                    int preId = stack.peekLast();
                    results[preId] += time - start;
                }
                start = time;
                stack.offerLast(id);
                continue;
            }
            stack.pollLast();
            results[id] += time - start + 1;
            start = time + 1;
        }
        return results;
    }
}