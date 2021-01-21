class Solution {
    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> toCount = new HashMap<>(50);
        for(int num : nums) {
            if(toCount.containsKey(num)) {
                toCount.put(num, toCount.get(num) + 1);
            } else {
                toCount.put(num, 1);
            }
        }

        List<Integer> dupCount = new ArrayList<>(50);
        for(int num : toCount.values()) {
            dupCount.add(num);
        }

        int n = quantity.length;
        int[] sum = new int[1 << n];
        for(int i = 1; i < (1 << n); ++i) {
            for(int j = 0; j < n; ++j) {
                if((i & (1 << j)) != 0) {
                    sum[i] = sum[i - (1 << j)] + quantity[j];
                    break;
                }
            }
        }

        int[][] dp = new int[dupCount.size() + 1][1 << n];
        for(int i = 0; i <= dupCount.size(); ++i) {
            dp[i][0] = 1;
        }

        for(int i = 0; i <= dupCount.size(); ++i) {
            for(int j = 1; j < (1 << n); ++j) {
                if(i != 0 && dp[i - 1][j] == 1) {
                    dp[i][j] = 1;
                    continue;
                }

                // s = (s - 1) & j 求 j 的子集
                for(int s = j; s != 0; s = (s - 1) & j) {
                    int prev = j - s;
                    boolean prevPart = (i == 0) ? (prev == 0) : (dp[i - 1][prev] == 1);
                    boolean need = (i == 0) ? false : dupCount.get(i - 1) >= sum[s];
                    if(prevPart && need) {
                        dp[i][j] = 1;
                        break;
                    }
                }
            }
        }
        return dp[dupCount.size()][(1 << n) - 1] == 1 ? true : false;
    }
}