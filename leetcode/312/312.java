class Solution {
    private Map<Integer, Integer> occured;
    private int ans = 0;
    public int maxCoins(int[] nums) {
        occured = new HashMap<>();
        int[] copyNums = new int[nums.length + 2];
        for(int i = 1; i < copyNums.length - 1; i++) {
            copyNums[i] = nums[i - 1];
        }
        copyNums[0] = 1;
        copyNums[copyNums.length - 1] = 1;
        int leftAns, rightAns;
        for(int i = 1; i < copyNums.length - 1; i++) {
            leftAns = findAns(copyNums, 0, i);
            rightAns = findAns(copyNums, i, copyNums.length - 1);
            ans = Math.max(leftAns + rightAns + copyNums[i], ans);
        }
        return ans;
    }

    int findAns(int[] copyNums, int left, int right) {
        if(occured.containsKey(right * 1000 + left) == true) {
            return occured.get(right * 1000 + left);
        }
        if(left + 1 == right) {
            return 0;
        }
        int leftAns, rightAns, ans = 0;
        for(int i = left + 1; i < right; i++) {
            leftAns = findAns(copyNums, left, i);
            rightAns = findAns(copyNums, i, right);
            ans = Math.max(ans, leftAns + rightAns + copyNums[i] * copyNums[left] * copyNums[right]);
        }
        occured.put(right * 1000 + left, ans);
        return ans;
    }
}