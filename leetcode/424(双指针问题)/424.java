class Solution {
    public int characterReplacement(String s, int k) {
        int n = s.length(), ans = 0;
        if(n == 0) {
            return 0;
        }
        int[] occurTime = new int[26];
        int left = 0, right = 0;
        occurTime[s.charAt(0) - 'A'] = 1;

        while(right < n) {
            int maxElement = findMaxElement(occurTime);
            if(right - left + 1 - maxElement > k) {
                --occurTime[s.charAt(left) - 'A'];
                ++left;
            } else {
                ans = Math.max(ans, right - left + 1);
                ++right;
                if(right < n) {
                   ++occurTime[s.charAt(right) - 'A'];
                }
            }
        }
        return ans;
    }

    private int findMaxElement(int[] occurTime) {
        int maxElement = 0;
        for(int i = 0; i < 26; ++i) {
            maxElement = Math.max(maxElement, occurTime[i]);
        }
        return maxElement;
    }
}