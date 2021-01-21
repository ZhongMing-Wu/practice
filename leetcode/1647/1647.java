class Solution {
    public int minDeletions(String s) {
        int[] occurTime = new int[27];
        for(int i = 0; i < s.length(); ++i) {
            ++occurTime[s.charAt(i) - 'a' + 1];
        }

        Arrays.sort(occurTime);
        int left = 26, right = 26, ans = 0, preSub = 0;
        while(occurTime[left] != 0) {
            preSub = 0;
            if(left != 26 && occurTime[left] >= occurTime[left + 1]) {
                preSub = occurTime[left] - occurTime[left + 1] + 1;
                if(preSub >  occurTime[left]) {
                    preSub = occurTime[left];
                }
            }
            int mark = occurTime[right];
            while(occurTime[left] == mark) { 
                occurTime[left] -= preSub;
                ans += preSub;
                --left;
            }
            int sub = 0;
            while(right != left) {
                if(occurTime[right] <= sub) {
                    occurTime[right] = 0;
                    ans += sub;
                    --right;
                    continue;
                }
                occurTime[right] -= sub;
                ans += sub;
                ++sub;
                --right;
            }
        }
        return ans;
    }
}