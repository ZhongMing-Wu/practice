class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        
        for(int i = 0; i < len; i++) {
            if(i == 0) {
                left[i] = 1;
            }
            else {
                if(ratings[i] > ratings[i - 1]) {
                    left[i] = left[ i -1] + 1;
                }
                else {
                    left[i] = 1;
                }
            }
        }
        for(int i = len - 1; i >= 0; i--) {
            if(i == len - 1) {
                right[i] = 1;
            }
            else {
                if(ratings[i] > ratings[i + 1]) {
                    right[i] = right[i + 1] + 1;
                }
                else {
                    right[i] = 1;
                }
            }
        }
        int ans = 0;
        for(int i = 0; i < len; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }
}