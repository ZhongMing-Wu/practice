class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] deleteNums1 = new int[n];
        int[] deleteNums2 = new int[n];
        List<Integer> dp = new ArrayList<>(n);

        for(int i = 0; i < n; ++i) {
            if(i == 0) {
                deleteNums1[i] = 0;
                dp.add(nums[i]);
                continue;
            }
            deleteNums1[i] = calculateDeleteCount(dp, nums[i], i + 1);
        }

        dp.clear();
        for(int i = n - 1; i >= 0; --i) {
            if(i == n - 1) {
                deleteNums2[i] = 0;
                dp.add(nums[i]);
                continue;
            }
            deleteNums2[i] = calculateDeleteCount(dp, nums[i], n - i);
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i < n - 1; ++i) {
            if(deleteNums1[i] == i || deleteNums2[i] == n - i - 1) {
                continue;
            }
            ans = Math.min(ans, deleteNums1[i] + deleteNums2[i]);
        }

        return ans;
    }

    private int calculateDeleteCount(List<Integer> dp, int num, int total) {
        int left = 0, right = dp.size(), middle;
        while(left < right) {
            middle = (left + right) / 2;
            if(dp.get(middle) < num) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        if(right == dp.size()) {
            dp.add(num);
        } else {
            dp.set(right, num);
        }
        return total - right - 1;
    }
}