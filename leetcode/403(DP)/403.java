class Solution {
    public boolean canCross(int[] stones) {
        if(stones[0] + 1 < stones[1]) {
            return false;
        }
        HashMap<Integer, HashSet<Integer>> dp = new HashMap<>();
        int n = stones.length;
        for(int i = 1; i < n; ++i) {
            dp.put(stones[i], new HashSet<Integer>());
        }
        dp.get(1).add(1);
        int curLocIndex = 1;
        while(curLocIndex < n - 1) {
            int curLoc = stones[curLocIndex];
            HashSet<Integer> stepLens = dp.get(curLoc);
            for(int stepLen : stepLens) {
                if(stepLen != 1 && dp.containsKey(curLoc + stepLen - 1)) {
                    dp.get(curLoc + stepLen - 1).add(stepLen - 1);
                }
                if(dp.containsKey(curLoc + stepLen)) {
                    dp.get(curLoc + stepLen).add(stepLen);
                }
                if(dp.containsKey(curLoc + stepLen + 1)) {
                    dp.get(curLoc + stepLen + 1).add(stepLen + 1);
                }
            }
            ++curLocIndex;
        }
        return !dp.get(stones[n - 1]).isEmpty();
    }
}