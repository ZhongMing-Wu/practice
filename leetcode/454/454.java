class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int len = A.length;
        Map<Integer, Integer> occurTimeAB = new HashMap<>(len * len);

        handle(A, B, len, occurTimeAB);

        int ans = 0;
        for(int c : C) {
            for(int d : D) {
                if(occurTimeAB.containsKey(-c - d)) {
                    ans += occurTimeAB.get(-c - d);
                }
            }
        }
        return ans;
    }

    private void handle(int[] arr1, int[] arr2, int len, Map<Integer, Integer> occurTime) {
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                int sum = arr1[i] + arr2[j];
                if(occurTime.containsKey(sum)) {
                    int curTime = occurTime.get(sum);
                    occurTime.put(sum, curTime + 1);
                } else {
                    occurTime.put(sum, 1);
                }
            }
        }
    }
}