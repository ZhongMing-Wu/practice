class Solution {
    public int longestSubstring(String s, int k) {
        if(k < 2) {
            return s.length();
        }
        if(s.length() < k) {
            return 0;
        }
        return calculateLongestSubstr(s, 0, s.length() - 1, k);
    }

    private int calculateLongestSubstr(String s, int start, int end, int k) {
        if(end - start + 1 < k) {
            return 0;
        }
        int[] charOccurTimes = new int[26];
        for(int i = start; i <= end; i++) {
            charOccurTimes[s.charAt(i) - 'a']++;
        }

        int maxLen;
        int left = start, right = end;
        while(right - left + 1 >= k && charOccurTimes[s.charAt(left) - 'a'] < k) {
            left++;
        }  
        while(right - left + 1 >= k && charOccurTimes[s.charAt(right) - 'a'] < k) {
            right--;
        }
        if(right - left + 1 < k) {
            return 0;
        }
        for(int i = left; i <= right; i++) {
            if(charOccurTimes[s.charAt(i) - 'a'] < k) {
                return Math.max(calculateLongestSubstr(s, left, i - 1, k), calculateLongestSubstr(s, i + 1, right, k));  
            }
        }
        return right - left + 1;
    }
}