class Solution {
    Map<Long, Integer> toRank;
    long[] sortPrefixNums;
    int[] treeArray;
    public int countRangeSum(int[] nums, int lower, int upper) {
        init(nums);
        long[] prefixSum = new long[nums.length];
        long sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            prefixSum[i] = sum;
        }

        int ans = 0;
        for(int i = 0; i < prefixSum.length; i++) {
            int amount1 = query(prefixSum[i] - upper, 0);
            int amount2 = query(prefixSum[i] - lower, 1);
            ans += (amount2 - amount1);
            if(prefixSum[i] >= lower && prefixSum[i] <= upper) {
                ans++;
            }
            update(prefixSum[i]);
        }
        return ans;
    }

    private void init(int[] nums) {
        Set<Long> prefixSum = new TreeSet<>();
        long sum = 0;
        for(int num : nums) {
            sum += num;
            prefixSum.add(sum);
        }
        
        int len = prefixSum.size();
        sortPrefixNums = new long[len];
        treeArray = new int[len + 1];
        toRank = new HashMap<>();

        int index = 0;
        for(long num : prefixSum) {
            sortPrefixNums[index] = num;
            toRank.put(num, index + 1);
            index++;
        }
    }
    
    private int query(long num, int flag) {
        int index = findleftNumIndex(sortPrefixNums, num, flag);
        if(index < 0) {
            return 0;
        }

        int rank = toRank.get(sortPrefixNums[index]);
        int total = 0;
        while(rank != 0) {
            total += treeArray[rank];
            rank -= lowBit(rank);
        }

        return total;
    }

    private void update(long num) {
        int rank = toRank.get(num);
        while(rank < treeArray.length) {
            treeArray[rank]++;
            rank += lowBit(rank);
        }
    } 

    private int lowBit(int num) {
        return num & (-num);
    }

    private int findleftNumIndex(long[] sortPrefixNums, long num, int flag) {
        int left = 0, right = sortPrefixNums.length - 1, middle;

        while(left <= right) {
            middle = (left + right) / 2;
            if(sortPrefixNums[middle] == num) {
                return flag == 0 ? middle - 1 : middle;
            }

            if(sortPrefixNums[middle] > num) {
                right = middle - 1;
                continue;
            }

            if(sortPrefixNums[middle] < num) {
                left = middle + 1;
            }
        }
        return right;
    }
}