class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;
        for(int weight : weights) {
            left = Math.max(weight, left);
            right += weight;
        }  
        while(left < right) {
            int mid = (right - left) / 2 + left;
            if(check(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

    public boolean check(int[] weights, int capacity, int days) {
        int curDays = 0, curCap = 0;
        for(int i = 0 ; i < weights.length; i++) {
            if(curCap + weights[i] <= capacity) {
                curCap += weights[i];
                continue;
            }
            curDays++;
            curCap = weights[i];
            if(curDays > days) {
                return false;
            }
        }
        if(curCap > 0 && curDays + 1 > days) {
            return false;
        }
        return true;
    }
}