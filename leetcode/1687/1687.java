class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] preWSum = new int[n + 1];
        // 一次性运送 0-i个货物的行程数
        int[] preCarryTimes = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            if(i == 1) {
                preWSum[i] = boxes[i - 1][1];
                preCarryTimes[i] = 2;
                continue;
            }
            preWSum[i] = preWSum[i - 1] + boxes[i - 1][1];
            preCarryTimes[i] = preCarryTimes[i - 1] + (boxes[i - 1][0] == boxes[i - 2][0] ? 0 : 1);
        }

        Deque<Integer> mQueue = new LinkedList<>();
        mQueue.offerLast(0);
        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            while(!mQueue.isEmpty() && (i - mQueue.peekFirst() > maxBoxes || preWSum[i] - preWSum[mQueue.peekFirst()] > maxWeight)) {
                mQueue.pollFirst();
            }

            int j = mQueue.peekFirst();
            dp[i] = dp[j] + preCarryTimes[i] - preCarryTimes[j + 1] + 2;

            if(i != n) {
                while(!mQueue.isEmpty()) {
                    j = mQueue.peekLast();
                    if(dp[j] - preCarryTimes[j + 1] < dp[i] - preCarryTimes[i + 1]) {
                        break;
                    } 
                    mQueue.pollLast();
                }
                mQueue.offerLast(i);
            }
        }
        return dp[n];
    }
}