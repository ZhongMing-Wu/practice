class Solution {
    public int reversePairs(int[] nums) {
        TreeSet<Long> tSet = new TreeSet<>();
        for(long num : nums) {
            tSet.add(num);
            tSet.add(num * 2);
        }
        int[] treeNums = new int[tSet.size() + 1];
        HashMap<Long, Integer> toRank = new HashMap<>();
        int rank = 1;
        for(long num : tSet) {
            toRank.put(num, rank);
            ++rank;
        }
        int count = 0;
        for(int i = nums.length - 1; i >= 0; --i) {
            long num = nums[i];
            rank = toRank.get(num);
            count += query(rank - 1, treeNums);
            rank = toRank.get(num * 2);
            update(rank, treeNums);
        }
        return count;
    }
    private int query(int index, int[] treeNums) {
        int sum = 0;
        while(index != 0) {
            sum += treeNums[index];
            index = (index - 1) & index;
        }
        return sum;
    }
    private void update(int index, int[] treeNums) {
        int n = treeNums.length;
        while(index < n) {
            ++treeNums[index];
            index = index + lowbit(index);
        }
    }
    private int lowbit(int num) {
        return num & (-num);
    }
}