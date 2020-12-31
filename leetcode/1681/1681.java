class Solution {
    public int minimumIncompatibility(int[] nums, int k) {
        int n = nums.length;
        int[] value = new int[1 << n];
        int[] accurTime = new int[n + 1];
        Arrays.fill(value, -1);
        for(int sub = 1; sub < (1 << n); sub++) {
            if(getBinaryCount(sub) == n / k) {
                for(int j = 0; j < n; j++) {
                    if((sub & (1 << j)) != 0) {
                        ++accurTime[nums[j]];
                    }
                }
                boolean flag = false;  // 默认没有重复元素
                int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
                for(int i = 1; i <= n; i++) {
                    if(accurTime[i] == 0) {
                        continue;
                    }
                    if(accurTime[i] > 1) {
                        flag = true;
                    }

                    minVal = Math.min(minVal, i);
                    maxVal = Math.max(maxVal, i);    
                
                    accurTime[i] = 0;
                }

                if(!flag) {
                    value[sub] = maxVal - minVal;
                }
            }
        }

        int[] dp = new int[1 << n];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int mask = 1; mask < (1 << n); mask++) {
            if(getBinaryCount(mask) % (n / k) != 0) {
                continue;
            }
            for(int sub = mask; sub > 0; sub = (sub - 1) & mask) {
                if(value[sub] != -1 && dp[mask ^ sub] != -1) {
                    if(dp[mask] == -1) {
                        dp[mask] = dp[mask ^ sub] + value[sub];
                    } else {
                        dp[mask] = Math.min(dp[mask], dp[mask ^ sub] + value[sub]);
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    } 

    private int getBinaryCount(int num) {
        int ans = 0;
        while(num != 0) {
            ans++;
            num = num & (num - 1);
        }
        return ans;
    }
}