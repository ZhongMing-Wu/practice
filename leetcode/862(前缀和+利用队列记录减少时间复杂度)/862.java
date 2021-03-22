class Solution {
    public int shortestSubarray(int[] A, int K) {
        Deque<Integer> candidate = new LinkedList<>();
        int n = A.length;
        int[] preSum = new int[n];
        preSum[0] = A[0];
        for(int i = 1; i < n; ++i) {
            preSum[i] = preSum[i - 1] + A[i];
        }

        int index = 0, ans = n + 1;
        while(index < n) {
            if(preSum[index] >= K) {
                ans = Math.min(ans, index + 1);
            }
            while(!candidate.isEmpty() && preSum[index] - preSum[candidate.peekFirst()] >= K) {
                ans = Math.min(ans, index - candidate.peekFirst());
                candidate.pollFirst();
            }
            // 当前 index > candidate.peekLast(), 如果 preSum[index] <= preSum[candidate.peekLast()]，那么前者一定优于后者，后者直接淘汰。
            while(!candidate.isEmpty() && preSum[index] <= preSum[candidate.peekLast()]) {
                candidate.pollLast();
            }
            candidate.offerLast(index);
            ++index;
        }
        return ans == n + 1 ? -1 : ans;
    }
}