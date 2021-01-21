class Solution {
    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int num : nums) {
            if(num % 2 == 1) {
                num *= 2;
            }
            set.add(num);
        }    
        int ans = Integer.MAX_VALUE;
        while(true) {
            int minVal = set.first();
            int maxVal = set.last();
            ans = Math.min(maxVal - minVal, ans);
            if(maxVal % 2 == 1) {
                break;
            }
            set.pollLast();
            set.add(maxVal / 2);
        }
        return ans;
    }
}