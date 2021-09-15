class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k == 0) {
            return 0;
        }
        HashMap<Character, Integer> toCount = new HashMap<>();
        int distinctCount = 0, left = 0, right = 0, maxLen = 0;
        while(right < s.length()) {
            if(!toCount.containsKey(s.charAt(right)) && distinctCount + 1 > k) {
                while(distinctCount == k) {
                    char c = s.charAt(left);
                    toCount.put(c, toCount.get(c) - 1);
                    left++;
                    if(toCount.get(c) == 0) {
                        distinctCount--;
                        toCount.remove(c);
                    }
                }
                continue;
            }
            char c = s.charAt(right);
            if(!toCount.containsKey(c)) {
                distinctCount++;
            }
            toCount.put(c, toCount.getOrDefault(c, 0) + 1);
            right++;
            maxLen = Math.max(right - left, maxLen);
        }

        maxLen = Math.max(right - left, maxLen);
        return maxLen;
    }
}