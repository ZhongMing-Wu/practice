class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> toCount = new HashMap<>();
        for(int num : nums) {
            toCount.put(num, toCount.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] num1, int[] num2) -> {
            return num1[1] - num2[1];
        });

        for(Map.Entry<Integer, Integer> entry : toCount.entrySet()) {
            int key = entry.getKey(), count = entry.getValue();
            if(queue.size() < k) {
                queue.offer(new int[] {key, count});
                continue;
            }
            if(queue.peek()[1] < count) {
                queue.poll();
                queue.offer(new int[] {key, count});
            }
        }

        int[] ret = new int[k];
        for(int i = 0; i < k; i++) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}