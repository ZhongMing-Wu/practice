class Solution {
    public int maxProduct(int[] nums) {
        int ans = Integer.MIN_VALUE, currentMax = 1, currentMin = 1, temp;

        for(int num : nums) {
            if(num < 0) {
                temp = currentMax;
                currentMax = currentMin;
                currentMin = temp;
            }
            currentMax = Math.max(currentMax * num, num);
            currentMin = Math.min(currentMin * num, num);

            ans = Math.max(ans, currentMax);
        }
        
        return ans;
    }
}