class Solution {
    int[] tree;
    Map<Integer, Integer> toRank;
    List<Integer> ans;
    public List<Integer> countSmaller(int[] nums) {
        ans = new ArrayList<>();
        if(0 == nums.length) {
            return ans;
        }

        tree = new int[nums.length + 1];
        initRankMap(nums);

        for(int i = nums.length - 1; i >= 0; i--) {
            int curRank = toRank.get(nums[i]);
            ans.add(query(curRank - 1));
            update(curRank);
        }

        Collections.reverse(ans);
        return ans;
    }

    private void initRankMap(int[] nums) {
        toRank = new HashMap<>(nums.length);
        Set<Integer> set = new TreeSet<>();

        for(int val : nums) {
            set.add(val);
        }

        int index = 1;
        for(int val : set) {
            toRank.put(val, index);
            index++;
        }
    }

    private int getRank(int num) {
        return toRank.get(num);
    }

    private int query(int curRank) {
        int sum = 0;
        while(curRank > 0) {
            sum += tree[curRank];
            curRank -= lowBit(curRank);
        }
        return sum;        
    }

    private void update(int curRank) {
        while(curRank <= tree.length - 1) {
            tree[curRank]++;
            curRank += lowBit(curRank);
        }
    }
    private int lowBit(int num) {
        return num & (-num);
    }
}